import { DepartmentDTO } from "@/entities/DepartmentDTO";
import axios from "axios";
import { onMounted, ref } from "vue";

export function useFetchDepartments() {
    
    const departments = ref([] as DepartmentDTO[]);
    const isDepartmentsLoading = ref(true);

    const fetchDepartments = async () => {

        try {

            const response = await axios.get('http://localhost:8080/api/departments');

            if (response.status !== 200) { throw new Error(); }

            departments.value = response.data;

        } 

        catch (error) { 

            if (axios.isAxiosError(error)) { alert(error.message); } 
            
            else { alert("Ooops, something went wrong!"); }
        
        }

        finally { isDepartmentsLoading.value = false; }

    };

    onMounted(fetchDepartments);

    return { departments, isDepartmentsLoading, fetchDepartments };
}