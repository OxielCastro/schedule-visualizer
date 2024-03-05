package edu.hanover.schedulevisualizer.conflict;

import edu.hanover.schedulevisualizer.core.entity.Section;

public class SingleSectionConstraints{
    private Section section;

    public SingleSectionConstraints(Section section) {
        this.section = section;
    }

    public boolean addSection(Section newSection) {
        if (newSection.equals(section)) {
            System.out.println("The section is already in Instructor Section list.");
            return false;
        } else {
            section = newSection;
            return true;
        }
    }

    public Section getSection() {
        return section;
    }

}
