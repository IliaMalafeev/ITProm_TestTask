import { onMounted, ref } from "vue";
import { DepartmentDTO } from "@/entities/DepartmentDTO";
import axios from "axios";

export function useFetchDepartment() {

    const department = ref({} as DepartmentDTO);
    const isDepartmentLoading = ref(true);
    const isDepartmentFound = ref(false);

    const fetchDepartment = async () => {

        try {
                    
            const departmentId = (window.location.href).split('/')[5];

            const response = await axios.get(`http://localhost:8080/api/departments/${departmentId}`);

            if (response.data === '') {
                isDepartmentFound.value = false;
                return;
            }

            department.value = response.data;
            isDepartmentFound.value = true;                
            
        } 
        
        catch (error) { 

            if (axios.isAxiosError(error)) { alert(error.message); } 
            
            else { alert("Ooops, something went wrong!"); }
        
        }
        
        finally { isDepartmentLoading.value = false; }

    };

    onMounted(fetchDepartment);

    return { department, isDepartmentLoading, isDepartmentFound, fetchDepartment };

}