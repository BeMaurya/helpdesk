import { ChangeEvent, useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { getUser } from "@/api/user";
import { addUser } from "@/store/features/user/userSlice";
import MainLayout from "@/components/layout/MainLayout";
import { useAuth } from "@/hook/Auth";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import TicketList from "@/components/TickeList/TicketList";
import { ChevronDownIcon } from "@radix-ui/react-icons";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuGroup,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import TicketDialog from "@/components/TicketDialog/TicketDialog";
import { SearchTypeEnum } from "@/enums/SearchTypeEnum";

const Dashboard = () => {
  // check auth
  useAuth();
  // Get the `user` state from Redux
  const dispatch = useDispatch();

  // state
  const [searchType, setSearchType] = useState<SearchTypeEnum>(
    SearchTypeEnum.TITLE
  );
  const [searchValue, setSearchValue] = useState<String>("");

  // Fetch user data when the component mounts
  useEffect(() => {
    // Assuming getUser returns a Promise<User>
    getUser(localStorage.getItem("token") || "")
      .then((res) => {
        // Dispatch an action to set the `user` state to the fetched user data.
        dispatch(addUser(res));
      })
      .catch((error) => {
        // Handle errors here
        console.error("Error fetching user data", error);
      });
  }, [dispatch]); // Include dispatch in the dependency array

  const handleSearch = (e: ChangeEvent<HTMLInputElement>) => {
    setSearchValue(e.target.value);
  };

  const handleSearchType = (type: SearchTypeEnum) => {
    setSearchType(type);
  };

  return (
    <MainLayout>
      <div className="flex flex-row justify-between mt-2">
        <div className="flex flex-row justify-between space-x-3">
          {/* TODO:  If you select a search with status and priority 
            in this section, a pop-up selection will be added and the 
            status and priority information retrieved via the API will 
            be shown and the search will be made accordingly.  */}
          <Input
            className="w-56"
            type="text"
            placeholder="search.."
            onChange={handleSearch}
          />
          <DropdownMenu>
            <DropdownMenuTrigger asChild>
              <Button>
                Search By {searchType}
                <ChevronDownIcon />
              </Button>
            </DropdownMenuTrigger>
            <DropdownMenuContent className="w-56">
              <DropdownMenuGroup>
                <DropdownMenuItem
                  onClick={() => handleSearchType(SearchTypeEnum.ID)}>
                  Id
                </DropdownMenuItem>
                <DropdownMenuItem
                  onClick={() => handleSearchType(SearchTypeEnum.TITLE)}>
                  Title
                </DropdownMenuItem>
                <DropdownMenuItem
                  onClick={() => handleSearchType(SearchTypeEnum.DESCRIPTION)}>
                  Description
                </DropdownMenuItem>
                <DropdownMenuItem
                  onClick={() => handleSearchType(SearchTypeEnum.STATUS)}>
                  Status
                </DropdownMenuItem>
                <DropdownMenuItem
                  onClick={() => handleSearchType(SearchTypeEnum.PRIORITY)}>
                  Priority
                </DropdownMenuItem>
                <DropdownMenuItem
                  onClick={() => handleSearchType(SearchTypeEnum.ASSIGNED_TO)}>
                  Assigned To
                </DropdownMenuItem>
              </DropdownMenuGroup>
            </DropdownMenuContent>
          </DropdownMenu>
        </div>
        <TicketDialog />
      </div>
      <div className="mt-2">
        <TicketList searchType={searchType} searchValue={searchValue} />
      </div>
    </MainLayout>
  );
};

export default Dashboard;
