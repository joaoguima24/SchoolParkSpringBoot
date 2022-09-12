package academy.mindswap.schoolpark.schoolpark.command;

import academy.mindswap.schoolpark.schoolpark.model.Teacher;

public class TeacherConverter {
    public static TeacherDTO convertToDTO (Teacher teacher){
        return TeacherDTO.builder()
                .ID(teacher.getID())
                .firstName(teacher.getFirstName())
                .lastName(teacher.getLastName())
                .email(teacher.getEmail())
                .build();
    }

    public static Teacher convertToEntity (TeacherDTO teacherDTO){
        return Teacher.builder()
                .ID(teacherDTO.getID())
                .firstName(teacherDTO.getFirstName())
                .lastName(teacherDTO.getLastName())
                .email(teacherDTO.getEmail())
                .build();
    }


    public static Teacher convertCreateTeacherDTOtoEntity(CreateTeacherDTO createTeacherDTO){
        return Teacher.builder()
                .firstName(createTeacherDTO.getFirstName())
                .lastName(createTeacherDTO.getLastName())
                .email(createTeacherDTO.getEmail())
                .password(createTeacherDTO.getPassword())
                .build();
    };
}
