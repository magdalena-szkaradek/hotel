package app;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomInfoController {

    @RequestMapping("/getAllRooms")
    public List<RoomInfo> getAllRoomInfo() {
        return Collections.emptyList();
    }
}
