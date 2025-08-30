package fold.model;

import java.util.*;

/**
 * The Java representation of a fold file.
 */
public class FoldFile {
    private final Map<String, Object> customPropertyMap = new HashMap<>();
    private double spec = 1.2;
    private String creator;
    private String author;
    private String title;
    private String description;
    private List<String> classes = new ArrayList<>();
    private FoldFrame rootFrame = new FoldFrame();
    private List<FoldFrame> frames = new ArrayList<>();

    /**
     * Custom properties.
     * <p>
     * To add custom data to the FOLD format specific to your software, include a colon (:) in the property key,
     * where the part before the colon identifies your software.
     *
     * @return The custom properties.
     */
    public Map<String, Object> getCustomPropertyMap() {
        return customPropertyMap;
    }

    /**
     * Set a single custom property in the custom properties map.
     * <p>
     * Read a value with the key {@code <namespace>:<key>} from the map.
     *
     * @param namespace The namespace for this property.
     * @param key       The key for this property.
     * @param value     The value for this property.
     */
    public void setCustomProperty(String namespace, String key, Object value) {
        customPropertyMap.put(formatCustomProperty(namespace, key), value);
    }

    /**
     * Get a single custom property from the custom properties map.
     * <p>
     * Set a value with the key {@code <namespace>:<key>} in the map.
     *
     * @param namespace The namespace for this property.
     * @param key       The key for this property.
     * @return The value for this property.
     */
    public Object getCustomProperty(String namespace, String key) {
        return customPropertyMap.getOrDefault(formatCustomProperty(namespace, key), null);
    }

    /**
     * Remove a single custom property from the custom properties map.
     * <p>
     * Remove a value with the key {@code <namespace>:<key>} from the map.
     *
     * @param namespace The namespace for this property.
     * @param key       The key for this property.
     */
    public void removeCustomProperty(String namespace, String key) {
        customPropertyMap.remove(formatCustomProperty(namespace, key));
    }

    private String formatCustomProperty(String namespace, String key) {
        return String.format("%s:%s", namespace, key);
    }

    /**
     * The version of the FOLD specification that the file assumes.
     * See the top of this spec for the current value. Strongly recommended,
     * in case we ever have to make backward-incompatible changes.
     *
     * @return The version of the FOLD specification.
     */
    public double getSpec() {
        return spec;
    }

    /**
     * Set the version of the FOLD specification.
     *
     * @param spec The version of the FOLD specification.
     * @see #getSpec()
     */
    public void setSpec(double spec) {
        this.spec = spec;
    }

    /**
     * The software that created the file. Recommended for files
     * output by computer software; less important for files made by hand.
     *
     * @return The software that create the file.
     */
    public String getCreator() {
        return creator;
    }

    /**
     * Set the software that created the file.
     *
     * @param creator The software that created the file.
     * @see #getCreator()
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * The human author.
     *
     * @return The human author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Set the human author.
     *
     * @param author The human author.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * A title for the entire file.
     *
     * @return The title for the entire file.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title for the file.
     *
     * @param title The title for the file.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * A description of the entire file.
     *
     * @return The description of the entire file.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description for the file.
     *
     * @param description The description for the file.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * A subjective interpretation about what the entire file represents.
     * Some standard file classes include:
     * <dl>
     * <dt>singleModel <dd> A single origami model, possibly still in multiple frames to represent crease pattern, folded form, etc.
     * <dt>multiModel <dd> Multiple origami models collected together into one file
     * <dt>animation <dd> Animation of sequence of frames, e.g., illustrating a continuous folding motion
     * <dt>diagrams <dd> A sequence of frames representing folding steps, as in origami diagrams
     * </dl>
     * <p>
     * Custom classes should have a colon in them; see Custom Properties below.
     *
     * @return The classes of this file.
     */
    public List<String> getClasses() {
        return classes;
    }

    /**
     * Set the classes for this file.
     *
     * @param classes The classes for this file.
     * @see #getClasses()
     */
    public void setClasses(List<String> classes) {
        this.classes = classes;
    }

    /**
     * Get the root frame of this file.
     * @return The root frame of this file.
     */
    public FoldFrame getRootFrame() {
        return rootFrame;
    }

    /**
     * Set the root frame of this file.
     * @param rootFrame The root frame of this file.
     */
    public void setRootFrame(FoldFrame rootFrame) {
        this.rootFrame = rootFrame;
    }

    /**
     * Array of frame dictionaries.
     *
     * @return The frames in this file.
     */
    public List<FoldFrame> getFrames() {
        return frames;
    }

    /**
     * Set the sub frames for this file.
     *
     * @param frames The sub frames for this file.
     */
    public void setFrames(List<FoldFrame> frames) {
        this.frames = frames;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoldFile foldFile = (FoldFile) o;
        return Double.compare(foldFile.getSpec(), getSpec()) == 0 && Objects.equals(getCustomPropertyMap(), foldFile.getCustomPropertyMap()) && Objects.equals(getCreator(), foldFile.getCreator()) && Objects.equals(getAuthor(), foldFile.getAuthor()) && Objects.equals(getTitle(), foldFile.getTitle()) && Objects.equals(getDescription(), foldFile.getDescription()) && Objects.equals(getClasses(), foldFile.getClasses()) && Objects.equals(getFrames(), foldFile.getFrames());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(getCustomPropertyMap(), getSpec(), getCreator(), getAuthor(), getTitle(), getDescription(), getClasses(), getFrames());
    }
}
