package P2MoviePlaylist;

public class LinkedMovieQueue extends LinkedQueue<Movie> {
    public boolean remove(String title) {
        Node prevNode = null;
        for(Node n = firstNode; n != null; n = n.next) {
            if(n.data.getTitle().equalsIgnoreCase(title)) {
                // case: want to remove first item
                if(n == firstNode) {
                    adjustStartTimesPast(n);
                    firstNode = firstNode.next;
                    return true;
                }
                // case: want to remove middle or last item
                adjustStartTimesPast(n);
                prevNode.next = n.next;
                return true;
            }
            // update previous node before n pointer moves on
            prevNode = n;
        }
        return false;
    }

    // helper method to avoid rewriting code
    public void adjustStartTimesPast(Node removedNode) {
        int time = removedNode.data.getStartTime();
        for(Node n = removedNode.next; n != null; n = n.next) {
            n.data.setStartTime(time);
            time += n.data.getDuration();
        }
    }
}
