    package edu.hanover.schedulevisualizer.core;

    import java.util.ArrayList;
    import java.util.List;

    public class Section {
        private static long nextAvailableCourseId = 0;
        private long sectionId;
        private Course course;
        private TimeSlot timeslot;
        private List<Instructor> instructorList = new ArrayList<>();

        protected Section() {}

        Section(final Course course, final TimeSlot timeslot) {
            this.sectionId = nextAvailableCourseId;
            nextAvailableCourseId += 1;
            this.course = course;
            this.timeslot = timeslot;
        }


        public String getPrefix() {
            return course.getPrefix();
        }

        public String getCourseNum() {
            return course.getCourseNum();
        }

        public String getCourseName() {
            return course.getCourseName();
        }

        public TimeSlot getTimeslot() { return timeslot; }

        public String getCourseCode() {
            return course.getCourseCode();
        }

        public List<Weekday> getWeekdays() { return timeslot.getWeekdayList(); }

        public long getSectionId() {
            return sectionId;
        }

        public void setTimeslot(final TimeSlot timeslot) {
            // TODO: Set observable
            this.timeslot = timeslot;
        }

        public List<Instructor> getInstructorList() {
            return instructorList;
        }

        public void addInstructor(final Instructor instructor) {
            if (!instructorList.contains(instructor)) {
                instructorList.add(instructor);
            }
        }

        public void changeInstructor(final Instructor oldInstructor, final Instructor newInstructor) {
            if (instructorList.contains(oldInstructor)) {
                removeInstructor(oldInstructor);
                addInstructor(newInstructor);
            }
        }

        public void removeInstructor(final Instructor instructor) {
            instructorList.remove(instructor);
        }

        public Boolean IsSameCourse(final Section section1) {
            return (section1.course.equals(this.course));
        }

        public boolean hasInstructor(final Instructor instr) {
            return this.getInstructorList().contains(instr);
        }

        public String makeString(){
            String acc = "";
            for (final Weekday weekday : timeslot.getWeekdayList()) {
                acc = acc + weekday.toShortString();
            }
            return course.getPrefix() + course.getCourseNum() + " " + acc + " " + timeslot.toString();
        }
    }
