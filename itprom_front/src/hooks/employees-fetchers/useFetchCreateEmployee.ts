import { Employee } from "@/entities/Employee";
import axios from "axios";
import { ref } from "vue";

export function useFetchCreateEmployee() {

    const employee = ref({} as Employee);
    const isCreateEmployeeLoading = ref(false);
    const httpError = ref("");

    const fetchCreateEmployee = async (employee: Employee) => {

        isCreateEmployeeLoading.value = true;

        try {

            const requestBody = JSON.parse(JSON.stringify(employee));

            const response = await axios.post('http://localhost:8080/api/employees/add-employee', requestBody);

            if (response.status === 201) { httpError.value = ""; }
            
        } 

        catch (error) { 

            if (axios.isAxiosError(error)) { httpError.value = error.response?.data.message; } 
            
            else { httpError.value = "Ooops, something went wrong!"; }
        
        }

        finally { isCreateEmployeeLoading.value = false; }

    };

    return { employee, isCreateEmployeeLoading, httpError, fetchCreateEmployee };
}