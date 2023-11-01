package bemaurya.helpdesk.business.concreates;

import bemaurya.helpdesk.business.abstracts.TicketServices;
import bemaurya.helpdesk.core.utilities.result.Result;
import bemaurya.helpdesk.core.utilities.result.ResultData;
import bemaurya.helpdesk.dataAccess.abstracts.TicketDAO;
import bemaurya.helpdesk.dto.request.ticket.TicketRequest;
import bemaurya.helpdesk.entities.concreates.*;
import bemaurya.helpdesk.security.JwtTokenProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketManager implements TicketServices {

    // this field injection
    private final TicketDAO ticketDAO;
    private final JwtTokenProvider jwtTokenProvider;

    // constructor injection
    public TicketManager(TicketDAO ticketDAO, JwtTokenProvider jwtTokenProvider) {
        this.ticketDAO = ticketDAO;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    // this method is used to add a ticket
    @Override
    public Result addTicket(TicketRequest ticket, String token) {
        if (!jwtTokenProvider.validateToken(token)){
            return new Result(false, "Token is not valid");
        }
        long userId = jwtTokenProvider.getIdFromToken(token);
        try {
            this.ticketDAO.save(Ticket.builder()
                    .title(ticket.getTitle())
                    .description(ticket.getDescription())
                    .priority(TicketPriority.builder().id(ticket.getPriority()).build())
                    .user(User.builder().id(userId).build())
                    .status(TicketStatus.builder().id(ticket.getStatus()).build())
                    .date(new Date())
                    .build());
        } catch (Exception e) {
            return new Result(false, "Ticket not added");
        }
        return new Result(true, "Ticket added");
    }

    // this method is used to delete a ticket
    @Override
    public Result deleteTicket(long id, String token) {
        if (!jwtTokenProvider.validateToken(token)){
            return new Result(false, "Token is not valid");
        }
        String role = jwtTokenProvider.getRoleFromToken(token);
        if (!role.equals("ADMIN")){
            return new Result(false, "You are not authorized");
        }
        try {
            this.ticketDAO.deleteById(id);
        } catch (Exception e) {
            return new Result(false, "Ticket not deleted");
        }
        return new Result(true, "Ticket deleted");
    }

    // this method is used get all tickets
    @Override
    public ResultData<List<Ticket>> getAllTicket(String token, int page, int size) {
        if (!jwtTokenProvider.validateToken(token)){
            return new ResultData<>(null, "Token is not valid", false);
        }
        String role = jwtTokenProvider.getRoleFromToken(token);
        if(role.equals(RoleEnum.ADMIN.toString()) || role.equals(RoleEnum.SUPPORT.toString())){
            PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("id"));
            Page<Ticket> ticketPage = ticketDAO.findAll(pageRequest);
            return new ResultData<>(ticketPage.getContent(), "Ticket list", true);
        } else {
            PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("id"));
            Page<Ticket> ticketPage = ticketDAO.findAllByUserId(jwtTokenProvider.getIdFromToken(token), pageRequest);
            return new ResultData<>(ticketPage.getContent(), "Ticket list", true);
        }
    }

    // this method is used to get ticket by id
    @Override
    public ResultData<Ticket> getTicketById(long id, String token) {
        if (!jwtTokenProvider.validateToken(token)) {
            return new ResultData<>(null, "Token is not valid", false);
        }

        String role = jwtTokenProvider.getRoleFromToken(token);
        Ticket ticket = ticketDAO.findById(id).get();

        boolean isAuthorized = ticket.getUser().getId() == jwtTokenProvider.getIdFromToken(token)
                || role.equals(RoleEnum.ADMIN.toString())
                || role.equals(RoleEnum.SUPPORT.toString());

        if (!isAuthorized) {
            return new ResultData<>(null, "You are not authorized", false);
        }
        return new ResultData<>(ticket, "Ticket", true);
    }

    // this method is used to get ticket by user id
    @Override
    public ResultData<List<Ticket>> getTicketByUserId(long id, int page , int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("id"));
        Page<Ticket> ticketPage = ticketDAO.findAllByUserId(id, pageRequest);
        return new ResultData<>(ticketPage.getContent(), "Ticket list", true);
    }

    // this method is used to update a ticket assignee
    @Override
    public Result addTicketAssignee(long ticketId, long assigneeId, String token) {
        if(!jwtTokenProvider.validateToken(token)){
            return new Result(false, "Token is not valid");
        }
        if (!jwtTokenProvider.getRoleFromToken(token).equals("ADMIN")){
            return new Result(false, "You are not authorized");
        }

        Ticket newTicket = ticketDAO.findById(ticketId).get();
        newTicket.setAssignedTo(User.builder().id(assigneeId).build());

        try{
            ticketDAO.save(newTicket);
        } catch (
                Exception e) {
            return new Result(false, "Ticket not added");
        }

        return new Result(true, "Ticket assignee added");
    }

    // this method is used to remove a ticket assignee
    @Override
    public Result removeTickerAssignee(long ticketId, String token) {
        if (!jwtTokenProvider.validateToken(token)){
            return new Result(false, "Token is not valid");
        }
        if (!jwtTokenProvider.getRoleFromToken(token).equals("ADMIN")){
            return new Result(false, "You are not authorized");
        }

        Ticket newTicket = ticketDAO.findById(ticketId).get();
        newTicket.setAssignedTo(null);
        try {
            ticketDAO.save(newTicket);
        } catch (Exception e) {
            return new Result(false, "Ticket not added");
        }
        return new Result(true, "Ticket assignee removed");
    }

}
