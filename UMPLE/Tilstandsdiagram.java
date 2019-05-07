//%% NEW FILE Expo BEGINS HERE %%

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4450.6749b7105 modeling language!*/



// line 2 "model.ump"
// line 31 "model.ump"
public class Expo
{

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Expo State Machines
    public enum Status { Expo, LoggedIn, Signup, Scan, Controlpanel, AddStand, AddExpo, Vote, Stand }
    private Status status;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Expo()
    {
        setStatus(Status.Expo);
    }

    //------------------------
    // INTERFACE
    //------------------------

    public String getStatusFullName()
    {
        String answer = status.toString();
        return answer;
    }

    public Status getStatus()
    {
        return status;
    }

    public boolean LoggingIn()
    {
        boolean wasEventProcessed = false;

        Status aStatus = status;
        switch (aStatus)
        {
            case Expo:
                setStatus(Status.LoggedIn);
                wasEventProcessed = true;
                break;
            default:
                // Other states do respond to this event
        }

        return wasEventProcessed;
    }

    public boolean IfUser()
    {
        boolean wasEventProcessed = false;

        Status aStatus = status;
        switch (aStatus)
        {
            case LoggedIn:
                setStatus(Status.Scan);
                wasEventProcessed = true;
                break;
            default:
                // Other states do respond to this event
        }

        return wasEventProcessed;
    }

    public boolean IfAdmin()
    {
        boolean wasEventProcessed = false;

        Status aStatus = status;
        switch (aStatus)
        {
            case LoggedIn:
                setStatus(Status.Controlpanel);
                wasEventProcessed = true;
                break;
            default:
                // Other states do respond to this event
        }

        return wasEventProcessed;
    }

    public boolean ScanQR()
    {
        boolean wasEventProcessed = false;

        Status aStatus = status;
        switch (aStatus)
        {
            case Scan:
                setStatus(Status.Stand);
                wasEventProcessed = true;
                break;
            default:
                // Other states do respond to this event
        }

        return wasEventProcessed;
    }

    public boolean AddStand()
    {
        boolean wasEventProcessed = false;

        Status aStatus = status;
        switch (aStatus)
        {
            case Controlpanel:
                setStatus(Status.AddStand);
                wasEventProcessed = true;
                break;
            default:
                // Other states do respond to this event
        }

        return wasEventProcessed;
    }

    public boolean AddExpo()
    {
        boolean wasEventProcessed = false;

        Status aStatus = status;
        switch (aStatus)
        {
            case Controlpanel:
                setStatus(Status.AddExpo);
                wasEventProcessed = true;
                break;
            default:
                // Other states do respond to this event
        }

        return wasEventProcessed;
    }

    private boolean __autotransition1382__()
    {
        boolean wasEventProcessed = false;

        Status aStatus = status;
        switch (aStatus)
        {
            case Vote:
                setStatus(Status.Scan);
                wasEventProcessed = true;
                break;
            default:
                // Other states do respond to this event
        }

        return wasEventProcessed;
    }

    public boolean Score0-5()
    {
        boolean wasEventProcessed = false;

        Status aStatus = status;
        switch (aStatus)
        {
            case Stand:
                setStatus(Status.Vote);
                wasEventProcessed = true;
                break;
            default:
                // Other states do respond to this event
        }

        return wasEventProcessed;
    }

    private void setStatus(Status aStatus)
    {
        status = aStatus;

        // entry actions and do activities
        switch(status)
        {
            case Vote:
                __autotransition1382__();
                break;
        }
    }

    public void delete()
    {}

}