package org.techmate.techmate_be.model.enumeration;

public enum CommitmentLevel {

    CONTRIBUTOR("Contributor", "Occasional contributions like bug fixes or small tasks."),
    PART_TIME_COLLABORATOR("Part-time Collaborator", "Regular contributor with flexible availability."),
    CORE_PARTNER("Core Partner", "Fully committed member involved in all aspects of the project."),
    ADVISOR("Advisor / Mentor", "Provides guidance or strategic advice, not actively coding."),
    TESTER("Tester / QA Volunteer", "Helps with testing and quality assurance."),
    OBSERVER("Observer", "Interested in following the project without direct contributions.");

    private final String label;
    private final String description;

    CommitmentLevel(String label, String description) {
        this.label = label;
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }
}
