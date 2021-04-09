package be.joengenduvel.codenature.web;

import be.joengenduvel.codenature.world.World;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/world")
public class WorldController {

    private final World world;

    public WorldController(World world) {
        this.world = world;
    }

    @RequestMapping(produces = "application/json")
    public WorldRepresentation getCompleteWorld(){
        world.tick();
        return WorldRepresentation.of(world);
    }
}
