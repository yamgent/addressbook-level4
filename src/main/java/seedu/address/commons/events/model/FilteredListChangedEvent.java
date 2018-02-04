package seedu.address.commons.events.model;

import javafx.collections.transformation.FilteredList;
import seedu.address.commons.events.BaseEvent;
import seedu.address.model.person.Person;

/**
 * Indicates the {@code FilteredList} in the model has changed
 */
public class FilteredListChangedEvent extends BaseEvent {
    public final FilteredList<Person> list;

    public FilteredListChangedEvent(FilteredList<Person> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "number of persons in filtered list " + list.size();
    }
}
