import {
  ColumnDef,
  flexRender,
  getCoreRowModel,
  useReactTable,
} from "@tanstack/react-table";

import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { Ticket } from "@/types/TicketType";
import { Config } from "@/types/Config";
import { useEffect, useState } from "react";
import axios from "axios";

interface TicketTableeProps<TData, TValue> {
  columns: ColumnDef<TData, TValue>[];
  data: TData[];
}

export function TicketTable<TData, TValue>({
  columns,
  data,
}: TicketTableeProps<TData, TValue>) {
  const table = useReactTable({
    data,
    columns,
    getCoreRowModel: getCoreRowModel(),
  });

  const [config, setConfig] = useState<Config | null>(null);

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/config/default", {
        headers: {
          token: localStorage.getItem("token"),
        },
      })
      .then((res) => {
        if (res.data.success && res.data.data.id !== 0) {
          setConfig(res.data.data);
        }
      });
  }, [config]);

  return (
    <div className="rounded-md border">
      <Table>
        <TableHeader>
          {table.getHeaderGroups().map((headerGroup) => (
            <TableRow key={headerGroup.id}>
              {headerGroup.headers.map((header) => {
                return (
                  <TableHead key={header.id}>
                    {header.isPlaceholder
                      ? null
                      : flexRender(
                          header.column.columnDef.header,
                          header.getContext()
                        )}
                  </TableHead>
                );
              })}
            </TableRow>
          ))}
        </TableHeader>
        <TableBody>
          {table.getRowModel().rows?.length ? (
            table.getRowModel().rows.map((row) => (
              <TableRow
                className="hover:bg-gray-100"
                key={row.id}
                data-state={row.getIsSelected() && "selected"}>
                {row.getVisibleCells().map((cell) => (
                  <TableCell
                    key={cell.id}
                    className={
                      config && cell.column.columnDef.header === "Status"
                        ? (cell.row.original as Ticket).status.status ===
                          config.openTicketStatus.status
                          ? "text-green-300"
                          : (cell.row.original as Ticket).status.status ===
                            config.closeTicketStatus.status
                          ? "text-red-500"
                          : ""
                        : ""
                    }>
                    {cell.column.columnDef.header === "Priority"
                      ? (cell.row.original as Ticket).priority.priority
                      : cell.column.columnDef.header === "Status"
                      ? (cell.row.original as Ticket).status.status
                      : cell.column.columnDef.header === "Assigned To"
                      ? (cell.row.original as Ticket).assignedTo?.email
                      : flexRender(
                          cell.column.columnDef.cell,
                          cell.getContext()
                        )}
                  </TableCell>
                ))}
              </TableRow>
            ))
          ) : (
            <TableRow>
              <TableCell colSpan={columns.length} className="h-24 text-center">
                No results.
              </TableCell>
            </TableRow>
          )}
        </TableBody>
      </Table>
    </div>
  );
}
