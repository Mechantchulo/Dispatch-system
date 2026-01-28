package service;

import model.RideRequest;
import java.util.LinkedList;
import java.util.Queue;

public class RideRequestService {
    
    // Queue to store all ride requests (FIFO - First In First Out)
    private Queue<RideRequest> rideQueue;
    
    // Constructor initializes the queue
    public RideRequestService() {
        rideQueue = new LinkedList<>();
    }
    
    /**
     * Adds a new ride request to the queue.
     * @param request The ride request to add
     */
    public void addRequest(RideRequest request) {
        rideQueue.add(request);
        System.out.println("Ride request added to queue: " + request.getId());
    }

    /**
     * Peeks at the next request in the queue without removing it.
     * @return The next RideRequest, or null if queue is empty.
     */
    public RideRequest peekNextRequest() {
        RideRequest request = rideQueue.peek();
        
        if (request != null) {
            System.out.println("Peeking at request: " + request.getId());
        } else {
            System.out.println("Queue is empty");
        }
        
        return request;
    }

    /**
     * Removes and returns the next request from the queue.
     * @return The next RideRequest
     */
    public RideRequest pollNextRequest() {
        RideRequest request = rideQueue.poll();
        
        if (request != null) {
            System.out.println("Removed request from queue: " + request.getId());
        }
        
        return request;
    }
    
    /**
     * Gets the number of pending requests.
     * @return Queue size
     */
    public int getPendingRequestCount() {
        return rideQueue.size();
    }
}
// THIS IS MEMBER 1(MUTUA) THAT COMMENTED THIS CODE. IT HAS SOME AMBIGUITY AND I DECIDED TO LEAVE IT THAT WAY FOR ERICK TO SORT IT AFTER MERGING
//package service;

// import model.RideRequest;
// import java.util.LinkedList;
// import java.util.Queue;


// public class RideRequestService {

//     // Queue to store all ride requests
//     private Queue<RideRequest> rideQueue;

//     // Constructor initializes the queue
//     public RideRequestService() {
//         rideQueue = new LinkedList<>();
//     }

//        public void addRequest(RideRequest request) {
//         rideQueue.add(request);
//     }
