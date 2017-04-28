pragma solidity ^0.4.10;
contract HandDesinfectionTracker {

    struct HandDesinfectionEvent {
        uint256 eventId;
        string deviceId;
        string employeeId;
        string chemistry;
        uint256 timestamp;
        uint256 duration;
    }

    address owner;

    uint256 eventIdSequence;
    HandDesinfectionEvent [] public desinfectionEvents;


    function HandDesinfectionTracker () {
        owner = msg.sender;
        eventIdSequence = 1;
    }

    function kill() {
        if (msg.sender == owner) {
            selfdestruct(owner);
        }
    }

    function trackHandDesinfectionEvent(string _deviceId, string _employeeId,
        string _chemistry, uint256 _timestamp, uint256 _duration) {
        checkAccess(msg.sender);

        HandDesinfectionEvent memory desinfectionEvent =
            HandDesinfectionEvent(eventIdSequence++, _deviceId, _employeeId, _chemistry, _timestamp, _duration);

        desinfectionEvents.push(desinfectionEvent);
    }

    function checkAccess(address sender) private {
        if (owner != sender) {
            throw;
        }
    }
}
