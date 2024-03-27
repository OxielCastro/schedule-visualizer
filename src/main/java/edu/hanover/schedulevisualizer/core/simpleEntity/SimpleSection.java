    package edu.hanover.schedulevisualizer.core.simpleEntity;

    import edu.hanover.schedulevisualizer.core.entity.TimeSlot;
    import edu.hanover.schedulevisualizer.core.entity.Weekday;
    import edu.hanover.schedulevisualizer.core.entity.Instructor;
    import edu.hanover.schedulevisualizer.core.entity.Section;

    import java.util.ArrayList;
    import java.util.List;

    public class SimpleSection implements Section {
        private static long nextAvailableSectionId = 0;
        private long sectionId;
        private SimpleSchedule schedule;
        private SimpleCourse course;
        private TimeSlot timeslot;
        private List<SimpleInstructor> instructorList = new ArrayList<>();

        protected SimpleSection() {}

        SimpleSection(final SimpleCourse course, final TimeSlot timeslot) {
            this.sectionId = nextAvailableSectionId;
            nextAvailableSectionId += 1;
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

        public SimpleCourse getCourse() { return course; }
        public List<Weekday> getWeekdays() { return timeslot.getWeekdayList(); }

        public long getSectionId() {
            return sectionId;
        }

        public void setTimeslot(final TimeSlot timeslot) {
            // TODO: Set observable
            this.timeslot = timeslot;
        }

        public List<? extends Instructor> getInstructorList() {
            return instructorList;
        }

        public void addInstructor(final Instructor instructor) {
            SimpleInstructor si = (SimpleInstructor) instructor;
            if (!instructorList.contains(si)) {
                instructorList.add(si);
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
            if (section1 instanceof SimpleSection) {
                return (((SimpleSection) section1).course.equals(this.course));
            }
            return false;
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
