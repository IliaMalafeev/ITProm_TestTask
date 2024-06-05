import { DepartmentDTO } from "@/entities/DepartmentDTO";
import axios from "axios";
import { ref } from "vue";

export function useFetchUpdateDepartment() {

    const isUpdateDepartmentLoading = ref(false);
    const httpError = ref("");

    const fetchUpdateDepartment = async (department: DepartmentDTO) => {

        isUpdateDepartmentLoading.value = true;

        try {

            const requestBody = JSON.parse(JSON.stringify(department));

            const response = await axios.put('http://localhost:8080/api/departments/update-department', requestBody);

            if (response.status === 200) { httpError.value = ""; }
            
        }

        catch (error) { 

            if (axios.isAxiosError(error)) { httpError.value = error.response?.data.message; } 
            
            else { httpError.value = "Ooops, something went wrong!"; }
        
        }

        finally { isUpdateDepartmentLoading.value = false; }

    };

    return { isUpdateDepartmentLoading, httpError, fetchUpdateDepartment };
}