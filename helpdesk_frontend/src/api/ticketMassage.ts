import { TicketMassageEndpoints } from "@/enums/APIEnum";
import axios from "axios";

export const addTicketMessage = async (
  token: string,
  ticketId: number | undefined,
  message: string
) => {
  const res = axios
    .post(
      TicketMassageEndpoints.AddTicketMassage,
      {
        ticketId: ticketId,
        message: message,
      },
      {
        headers: {
          token: token,
        },
      }
    )
    .then((res) => {
      return res.data.success;
    });
  return res;
};

export const deleteTicketMessage = async (id: number, token: string) => {
  const res = axios
    .delete(TicketMassageEndpoints.DeleteTicketMassage + id, {
      headers: {
        token: token,
      },
    })
    .then((res) => {
      return res.data.success;
    });
  return res;
};
