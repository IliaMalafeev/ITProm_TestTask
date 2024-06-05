import router from "@/router";
import axios from "axios";

export function useFetchDeleteEmployee() {

    const fetchDeleteEmployee = async () => {

        try {

            const employeeId = (window.location.href).split('/')[5];

            const response = await axios.delete(`http://localhost:8080/api/employees/delete-employee/${employeeId}`);

            if (response.status === 204) { router.replace('/'); }
            
        } 

        catch (error) { 

            if (axios.isAxiosError(error)) { alert(error.message); } 
            
            else { alert("Ooops, something went wrong!"); }
        
        }

    };

    return { fetchDeleteEmployee };

}