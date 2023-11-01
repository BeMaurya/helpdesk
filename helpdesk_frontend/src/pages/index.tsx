/* eslint-disable @next/next/no-img-element */
import { Button } from "@/components/ui/button";
import { Form, FormField, FormMessage } from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import * as z from "zod";
import axios from "axios";
import { AuthEndpoints } from "@/enums/APIEnum";
import { useState } from "react";
import { useAuth } from "@/hook/Auth";
import { Skeleton } from "@/components/ui/skeleton";

// form validation schema
const formSchema = z.object({
  email: z.string().email().min(2, "Email must be at least 6 characters"),
  password: z.string().min(6, "Password must be at least 6 characters"),
});

const Home = () => {
  // check if user is authenticated

  // error state
  const [error, setError] = useState<string>("");

  // redirect to dashboard if user is authenticated
  useAuth();

  // form hook
  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      email: "",
      password: "",
    },
  });

  // form submit handler
  const onSubmit = (data: z.infer<typeof formSchema>) => {
    axios.post(AuthEndpoints.Login, data).then((res) => {
      if (res.data.success) {
        localStorage.setItem("token", res.data.data);
        setError("");
        window.location.href = "/dashboard";
      } else {
        setError(res.data.message);
      }
    });
  };

  return (
    <main className="max-w[1440px] h-[100vh]  flex flex-row items-center justify-center">
      {useAuth() ? (
        <div className="flex flex-col items-center space-x-4 space-y-2">
          <Skeleton className="h-12 w-12" />
          <div className="space-y-2">
            <Skeleton className="h-4 w-[250px]" />
            <Skeleton className="h-4 w-[250px]" />
            <Skeleton className="h-[25px] w-[100px]" />
          </div>
        </div>
      ) : (
        <div>
          <h1 className="text-4xl font-bold text-center">Login</h1>
          <p className="text-center">Welcome back</p>
          <Form {...form}>
            <form
              onSubmit={form.handleSubmit(onSubmit)}
              className="space-y-1 min-w-[300px]">
              <FormField
                name="email"
                control={form.control}
                render={({ field }) => (
                  <Label>
                    Email
                    <Input {...field} />
                  </Label>
                )}
              />
              <FormMessage>{form.formState.errors.email?.message}</FormMessage>
              <FormField
                name="password"
                control={form.control}
                render={({ field }) => (
                  <Label>
                    Password
                    <Input type="password" {...field} />
                  </Label>
                )}
              />
              <FormMessage>
                {form.formState.errors.password?.message}
              </FormMessage>
              <Button type="submit">Login</Button>
              <FormMessage>{error}</FormMessage>
            </form>
          </Form>
        </div>
      )}
    </main>
  );
};

export default Home;
