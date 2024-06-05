import { DepartmentDTO } from "@/entities/DepartmentDTO";
import axios from "axios";
import { ref } from "vue";

export function useFetchCreateDepartment() {

    const department = ref({} as DepartmentDTO);
    const isCreateDepartmentLoading = ref(false);
    const httpError = ref("");

    const fetchCreateDepartment = async (department: DepartmentDTO) => {

        isCreateDepartmentLoading.value = true;

        try {

            const requestBody = JSON.parse(JSON.stringify(department));

            const response = await axios.post('http://localhost:8080/api/departments/add-department', requestBody);

            if (response.status === 201) { httpError.value = ""; }
            
        } 

        catch (error) { 

            if (axios.isAxiosError(error)) { httpError.value = error.response?.data.message; } 
            
            else { httpError.value = "Ooops, something went wrong!"; }
        
        }

        finally { isCreateDepartmentLoading.value = false; }

    };

    return { department, isCreateDepartmentLoading, httpError, fetchCreateDepartment };
}