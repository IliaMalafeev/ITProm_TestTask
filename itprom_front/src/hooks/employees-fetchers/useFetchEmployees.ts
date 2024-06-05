import { onMounted, ref } from "vue";
import { Employee } from "@/entities/Employee";
import axios from "axios";

export function useFetchEmployees() {

    const employees = ref([] as Employee[]);
    const isEmployeesLoading = ref(true);

    const fetchEmployees = async () => {

        try {                    

            const response = await axios.get('http://localhost:8080/api/employees');

            if (response.status !== 200) { throw new Error(); }

            employees.value = response.data;                 
            
        } 
        
        catch (error) { 

            if (axios.isAxiosError(error)) { alert(error.message); } 
            
            else { alert("Ooops, something went wrong!"); }
        
        }
        
        finally { isEmployeesLoading.value = false; }

    };

    onMounted(fetchEmployees);

    return { employees, isEmployeesLoading, fetchEmployees };

}