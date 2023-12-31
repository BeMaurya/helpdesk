import { TicketEndpoints } from "@/enums/APIEnum";
import axios from "axios";

export const getAllTickets = async (token: string, page: number) => {
  const res = axios
    .get(TicketEndpoints.GetAllTickets + `${page}/24`, {
      headers: {
        token: token,
      },
    })
    .then((res) => {
      if (res.data.success) {
        return res.data.data;
      } else {
        return [];
      }
    });
  return res;
};

export const getTicketById = async (token: string, id: string | string[]) => {
  const res = axios
    .get(TicketEndpoints.GetTicketById + id, {
      headers: {
        token: token,
      },
    })
    .then((res) => {
      if (res.data.success) {
        return res.data.data;
      }
    });
  return res;
};

// closed ticket status
export const closedTicket = async (token: string, id: number) => {
  const res = axios
    .patch(
      TicketEndpoints.CloseTicket + id,
      {},
      {
        headers: {
          token: token,
        },
      }
    )
    .then((res) => {
      if (res.data.success) {
        return res.data;
      } else {
        return res.data;
      }
    });
  return res;
};