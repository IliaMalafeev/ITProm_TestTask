import router from "@/router";
import axios from "axios";

export function useFetchDeleteDepartment() {

    const fetchDeleteDepartment = async () => {

        try {

            const departmentId = (window.location.href).split('/')[5];

            const response = await axios.delete(`http://localhost:8080/api/departments/delete-department/${departmentId}`);

            if (response.status === 204) { router.replace('/departments'); } 
            
        } 

        catch (error) { 

            if (axios.isAxiosError(error)) { alert(error.message); } 
            
            else { alert("Ooops, something went wrong!"); }
        
        }

    };

    return { fetchDeleteDepartment };

}