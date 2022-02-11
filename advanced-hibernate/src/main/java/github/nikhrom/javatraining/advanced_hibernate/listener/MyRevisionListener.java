package github.nikhrom.javatraining.advanced_hibernate.listener;

import github.nikhrom.javatraining.advanced_hibernate.entity.Revision;
import org.hibernate.envers.RevisionListener;

public class MyRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object o) {
        //SecurityContext.getUser().getId();
        ((Revision) o).setUsername("nikhrom");
    }
}
