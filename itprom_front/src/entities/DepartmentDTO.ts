export class DepartmentDTO {

    id?: number;
    title: string;
    additionalInfo: string;
    parentDepartment: DepartmentDTO;

    constructor (title: string, additionalInfo: string, parentDepartment: DepartmentDTO, id?: number) {

        this.id = id;
        this.title = title;
        this.additionalInfo = additionalInfo;
        this.parentDepartment = parentDepartment;
        
    }
    
}