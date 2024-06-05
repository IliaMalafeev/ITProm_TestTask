import { Employee } from "@/entities/Employee";
import axios from "axios";
import { ref } from "vue";

export function useFetchUpdateEmployee() {

    const isUpdateEmployeeLoading = ref(false);
    const httpError = ref("");

    const fetchUpdateEmployee = async (employee: Employee) => {

        isUpdateEmployeeLoading.value = true;

        try {

            const requestBody = JSON.parse(JSON.stringify(employee));

            const response = await axios.put('http://localhost:8080/api/employees/update-employee', requestBody);

            if (response.status === 200) { httpError.value = ""; }
            
        }

        catch (error) { 

            if (axios.isAxiosError(error)) { httpError.value = error.response?.data.message; } 
            
            else { httpError.value = "Ooops, something went wrong!"; }
        
        }

        finally { isUpdateEmployeeLoading.value = false; }

    };

    return { isUpdateEmployeeLoading, httpError, fetchUpdateEmployee };
}