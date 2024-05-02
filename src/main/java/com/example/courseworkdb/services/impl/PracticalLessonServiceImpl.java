package com.example.courseworkdb.services.impl;

import com.example.courseworkdb.entities.Enrollment;
import com.example.courseworkdb.entities.Instructor;
import com.example.courseworkdb.entities.PracticalLesson;
import com.example.courseworkdb.entities.TheoreticalLesson;
import com.example.courseworkdb.repositories.PracticalLessonRepository;
import com.example.courseworkdb.repositories.TheoreticalLessonRepository;
import com.example.courseworkdb.services.PracticalLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PracticalLessonServiceImpl implements PracticalLessonService {
    final
    TheoreticalLessonRepository theoreticalLessonRepository;
    final
    PracticalLessonRepository practicalLessonRepository;
    final
    InstructorServiceImpl instructorService;
    final
    CarServiceImpl carService;
    final
    EnrollmentServiceImpl enrollmentService;
    public PracticalLessonServiceImpl(PracticalLessonRepository practicalLessonRepository, CarServiceImpl carService, InstructorServiceImpl instructorService, EnrollmentServiceImpl enrollmentService, TheoreticalLessonRepository theoreticalLessonRepository) {
        this.practicalLessonRepository = practicalLessonRepository;
        this.carService = carService;
        this.instructorService = instructorService;
        this.enrollmentService = enrollmentService;
        this.theoreticalLessonRepository = theoreticalLessonRepository;
    }



    @Override
    public List<PracticalLesson> getAll() {
        return practicalLessonRepository.findAll();
    }

    @Override
    public Optional<PracticalLesson> getById(int id) {
        return practicalLessonRepository.findById(id);
    }

    @Override
    public void create(PracticalLesson obj) {
        practicalLessonRepository.save(obj);
    }

    @Override
    public void deleteById(int id) {
        practicalLessonRepository.deleteById(id);
    }

    @Override
    public void update(int id, PracticalLesson obj) {

    }
    public List<PracticalLesson> getAllByStudentId(int id){
        return practicalLessonRepository.findByEnrollment_Student_Id(id);
    }

    public void generateAllLessons(){
        Random random=new Random();
        List<Enrollment>enrollmentList=enrollmentService.getAll();
        for (Enrollment enrollment:
             enrollmentList) {
            if(Objects.equals(enrollment.getCourse().getCourseType(), "practical")){
                generationLessons(enrollment, random.nextInt(1,enrollment.getCourse().getDurationHours()+1));
            }
        }
    }

    public void generationLessons(Enrollment enrollment, int count){
        Random random=new Random();
        LocalDateTime startDate = enrollment.getEnrollmentDate().atZone(ZoneOffset.UTC).toLocalDateTime();
        LocalDateTime endDate = startDate.plusMonths(random.nextInt(2,4));
        for(int i=0;i<count;i++) {
            PracticalLesson practicalLesson = new PracticalLesson();
            //заповнення полів
            long startSeconds = startDate.toEpochSecond(ZoneOffset.UTC);
            long endSeconds = endDate.toEpochSecond(ZoneOffset.UTC);
            long randomSeconds = ThreadLocalRandom.current().nextLong(startSeconds, endSeconds);
            LocalDateTime randomDate = LocalDateTime.ofEpochSecond(randomSeconds, 0, ZoneOffset.UTC);

            practicalLesson.setStartDate(randomDate.withMinute(0).withSecond(0).toInstant(ZoneOffset.UTC)); // Встановлюємо години, хвилини і секунди
            practicalLesson.setCar(carService.getRandom());
            practicalLesson.setInstructor(instructorService.getRandom());
            practicalLesson.setDurationHours(1);
            practicalLesson.setEnrollment(enrollment);
            if(practicalLessonRepository.findByInstructorAndStartDate(practicalLesson.getInstructor(), practicalLesson.getStartDate()).isPresent()
            || practicalLessonRepository.findByStartDateAndCar(practicalLesson.getStartDate(), practicalLesson.getCar()).isPresent()
            || theoreticalLessonRepository.findByInstructorAndStartDate(practicalLesson.getInstructor(),practicalLesson.getStartDate()).isPresent()){
                count++;
                continue;
            }
            practicalLessonRepository.save(practicalLesson);

        }
    }
}
