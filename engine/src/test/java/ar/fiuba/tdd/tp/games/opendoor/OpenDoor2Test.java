package ar.fiuba.tdd.tp.games.opendoor;

/**
 * Created by swandelow on 4/22/16.
 */
public class OpenDoor2Test {
/*
    private OpenDoor2 target = new OpenDoor2();

    @Test
    public void testHappyPath() {

        String response =  this.testStart();

        response = this.target.play(new Command(ActionOld.OPEN, "box"));
        assertEquals("The box is opened!", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(ActionOld.LOOK_AROUND, ""));
        assertEquals("Items in the room: box, door, key.", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(ActionOld.PICK, "key"));
        assertEquals("There you go!", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(ActionOld.OPEN, "door"));
        assertEquals("You enter room 2. You won the game!", response);
        assertTrue(this.target.isFinished());
    }

    private String testStart() {
        String response = this.target.start();
        assertEquals("Welcome to OpenDoor2!", response);
        assertFalse(this.target.isFinished());
        return response;
    }

    @Test
    public void testLookAround() {

        String response =  this.testStart();

        response = this.target.play(new Command(ActionOld.LOOK_AROUND, ""));
        assertEquals("Items in the room: box, door.", response);
        assertFalse(this.target.isFinished());
    }

    @Test
    public void testOpenBox() {
        String response =  this.testStart();

        response = this.target.play(new Command(ActionOld.OPEN, "door"));
        assertEquals("Ey! Where do you go?! Room 2 is locked.", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(ActionOld.EXAMINE, "box"));
        assertEquals("You can open/close the box.", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(ActionOld.OPEN, "box"));
        assertEquals("The box is opened!", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(ActionOld.LOOK_AROUND, ""));
        assertEquals("Items in the room: box, door, key.", response);
        assertFalse(this.target.isFinished());
    }

    @Test
    public void testInvalidCommand() {

        this.target.start();

        String response = this.target.play(new Command(ActionOld.UNKNOWN_ACTION, ""));
        assertEquals("Unknown command.", response);
        assertFalse(this.target.isFinished());

    }*/
}
