import { ConfigEndpoints } from "@/enums/APIEnum";
import axios from "axios";

export const getDefaultConfigList = async (token: string) => {
  const res = await axios
    .get(ConfigEndpoints.GetConfigList, {
      headers: {
        token: token,
      },
    })
    .then((res) => {
      if (res.data.success) {
        return res.data.data;
      } else {
        return null;
      }
    });
  return res;
};

export const getDefaultConfig = async (token: string) => {
  const res = await axios
    .get(ConfigEndpoints.GetDefaultConfig, {
      headers: {
        token: token,
      },
    })
    .then((res) => {
      if (res.data.success) {
        return res.data.data;
      } else {
        return null;
      }
    });
  return res;
};

export const updateDefaultConfig = async (token: string, id: number) => {
  const res = await axios
    .patch(
      ConfigEndpoints.UpdateDefaultConfig + id,
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
        return null;
      }
    });
  return res;
};

export const addConfig = async (
  token: string,
  openTicketStatusId: number,
  closeTicketStatusId: number
) => {
  const res = await axios
    .post(
      ConfigEndpoints.AddConfig,
      {
        openTicketStatusId: openTicketStatusId,
        closeTicketStatusId: closeTicketStatusId,
      },
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
        return null;
      }
    });
  return res;
};

export const deleteConfig = async (token: string, id: number) => {
  const res = await axios
    .delete(ConfigEndpoints.DeleteConfig + id, {
      headers: {
        token: token,
      },
    })
    .then((res) => {
      if (res.data.success) {
        return res.data;
      } else {
        return null;
      }
    });
  return res;
};
