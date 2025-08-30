package fold.model;

/**
 * For an edge, representing its fold direction assignment.
 */
public enum FoldEdgeAssignment {
    /**
     * Border/boundary edge
     */
    BORDER("B"),
    /**
     * Mountain fold
     */
    MOUNTAIN_FOLD("M"),
    /**
     * Valley fold
     */
    VALLEY_FOLD("V"),
    /**
     * Flat (unfolded) fold
     */
    FLAT_FOLD("F"),
    /**
     * Unassigned/unknown
     */
    UNASSIGNED("U"),
    /**
     * Join edge
     * @since 1.2
     */
    JOIN("J"),
    /**
     * Cut/slit edge
     * @since 1.2
     */
    CUT("C"),
    ;

    private final String letter;

    FoldEdgeAssignment(String letter) {
        this.letter = letter;
    }

    /**
     * Create a new instance using the letter saved in the fold file.
     *
     * @param letter Letter identifying this FoldEdgeAssignment.
     * @return A FoldEdgeAssignment or null.
     */
    public static FoldEdgeAssignment of(String letter) {
        for (FoldEdgeAssignment foldEdgeAssignment : values()) {
            if (foldEdgeAssignment.getLetter().equals(letter)) {
                return foldEdgeAssignment;
            }
        }

        return null;
    }

    /**
     * Get the letter associated with this FoldEdgeAssignment, corresponds to the value in the actual fold file.
     *
     * @return Letter associated with this FoldEdgeAssignment.
     */
    public String getLetter() {
        return letter;
    }
}
