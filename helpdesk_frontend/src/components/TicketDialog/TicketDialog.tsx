import { TicketPriority } from "@/types/TicketPriorityType";
import { Button } from "../ui/button";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTrigger,
} from "../ui/dialog";
import { Input } from "../ui/input";
import { useEffect, useState } from "react";
import axios from "axios";
import { TicketEndpoints, TicketPriorityEndpoints } from "@/enums/APIEnum";
import { Label } from "@radix-ui/react-dropdown-menu";
import { z } from "zod";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { Form, FormField, FormMessage } from "../ui/form";
import { Textarea } from "../ui/textarea";

// formSchema validation
const formSchema = z.object({
  title: z
    .string()
    .min(3, {
      message: "Title must be at least 3 characters long",
    })
    .max(50, {
      message: "Title must be less than 50 characters long",
    }),
  description: z.string().min(3, {
    message: "Description must be at least 3 characters long",
  }),
  priority: z.string().min(1, {
    message: "Please select a priority",
  }),
});

const TicketDialog = () => {
  // state
  const [priority, setPriority] = useState<TicketPriority[]>();

  // form hook
  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      title: "",
      description: "",
      priority: "",
    },
  });

  useEffect(() => {
    axios
      .get(TicketPriorityEndpoints.GetAllTicketPriorities, {
        headers: {
          token: localStorage.getItem("token"),
        },
      })
      .then((res) => {
        setPriority(res.data.data);
      });
  }, []);

  const onSubmit = (data: z.infer<typeof formSchema>) => {
    const formData = {
      ...data,
      priority: parseInt(data.priority.toString()),
    };
    axios
      .post(
        TicketEndpoints.AddTicket,
        {
          title: formData.title,
          description: formData.description,
          priority: formData.priority,
          status: 1,
        },
        {
          headers: {
            token: localStorage.getItem("token"),
          },
        }
      )
      .then((res) => {
        console.log(res.data);
      });
    form.reset();
    window.location.reload();
  };

  return (
    <div>
      <Dialog>
        <DialogTrigger asChild>
          <Button>New Ticket</Button>
        </DialogTrigger>
        <DialogContent>
          <DialogHeader>New Ticket</DialogHeader>
          <DialogDescription>create a new ticket here</DialogDescription>
          <Form {...form}>
            <form
              className="flex flex-col space-y-3"
              onSubmit={form.handleSubmit(onSubmit)}>
              <FormField
                name="title"
                control={form.control}
                render={({ field }) => (
                  <Label>
                    Title
                    <Input {...field} placeholder="Title" />
                  </Label>
                )}
              />
              <FormMessage>{form.formState.errors.title?.message}</FormMessage>
              <FormField
                name="description"
                control={form.control}
                render={({ field }) => (
                  <Label>
                    Description
                    <Textarea {...field} placeholder="Description" />
                  </Label>
                )}
              />
              <FormMessage>
                {form.formState.errors.description?.message}
              </FormMessage>
              <FormField
                name="priority"
                control={form.control}
                render={({ field }) => (
                  <Label>
                    Priority
                    <select
                      defaultValue=""
                      {...field}
                      className="flex h-9 w-full items-center justify-between rounded-md border border-input bg-transparent px-3 py-2 text-sm shadow-sm ring-offset-background placeholder:text-muted-foreground focus:outline-none focus:ring-1 focus:ring-ring disabled:cursor-not-allowed disabled:opacity-50">
                      <option value="" disabled>
                        Select a priority
                      </option>
                      {priority &&
                        priority.map((p) => {
                          return (
                            <option key={p.id} value={p.id}>
                              {p.priority}
                            </option>
                          );
                        })}
                    </select>
                    <FormMessage>
                      {form.formState.errors.priority?.message}
                    </FormMessage>
                  </Label>
                )}
              />
              <Button type="submit">Submit</Button>
            </form>
          </Form>
        </DialogContent>
      </Dialog>
    </div>
  );
};

export default TicketDialog;
