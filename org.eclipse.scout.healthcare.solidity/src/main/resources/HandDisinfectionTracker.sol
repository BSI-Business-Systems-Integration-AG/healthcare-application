pragma solidity ^0.4.6;
contract HandDisinfectionTracker {

    struct HandDisinfectionEvent {
        uint256 eventId;
        string deviceId;
        string employeeId;
        string chemistry;
        uint256 timestamp;
        uint256 duration;
        string eventUuid;
    }

    address owner;

    uint256 eventIdSequence;
    HandDisinfectionEvent [] public disinfectionEvents;

    function HandDisinfectionTracker () {
        owner = msg.sender;
        eventIdSequence = 1;
    }

    function kill() {
        if (msg.sender == owner) {
            selfdestruct(owner);
        }
    }

    function trackHandDisinfectionEvent(string _deviceId, string _employeeId,
        string _chemistry, uint256 _timestamp, uint256 _duration, string _eventUuid) {
        checkAccess(msg.sender);

        HandDisinfectionEvent memory DisinfectionEvent =
            HandDisinfectionEvent(eventIdSequence++, _deviceId, _employeeId, _chemistry, _timestamp, _duration, _eventUuid);

        disinfectionEvents.push(DisinfectionEvent);
    }

    function checkAccess(address sender) private {
        if (owner != sender) {
            throw;
        }
    }
}
