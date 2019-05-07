//%% NEW FILE Stand BEGINS HERE %%

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4450.6749b7105 modeling language!*/


import java.util.*;

// line 8 "model.ump"
// line 36 "model.ump"
public class Stand
{

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Stand Attributes
    private String standName;

    //Stand Associations
    private Expo expo;
    private List<User> owner;
    private List<Vote> votes;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Stand(String aStandName, Expo aExpo)
    {
        standName = aStandName;
        boolean didAddExpo = setExpo(aExpo);
        if (!didAddExpo)
        {
            throw new RuntimeException("Unable to create stand due to expo");
        }
        owner = new ArrayList<User>();
        votes = new ArrayList<Vote>();
    }

    //------------------------
    // INTERFACE
    //------------------------

    public boolean setStandName(String aStandName)
    {
        boolean wasSet = false;
        standName = aStandName;
        wasSet = true;
        return wasSet;
    }

    public String getStandName()
    {
        return standName;
    }
    /* Code from template association_GetOne */
    public Expo getExpo()
    {
        return expo;
    }
    /* Code from template association_GetMany */
    public User getOwner(int index)
    {
        User aOwner = owner.get(index);
        return aOwner;
    }

    public List<User> getOwner()
    {
        List<User> newOwner = Collections.unmodifiableList(owner);
        return newOwner;
    }

    public int numberOfOwner()
    {
        int number = owner.size();
        return number;
    }

    public boolean hasOwner()
    {
        boolean has = owner.size() > 0;
        return has;
    }

    public int indexOfOwner(User aOwner)
    {
        int index = owner.indexOf(aOwner);
        return index;
    }
    /* Code from template association_GetMany */
    public Vote getVote(int index)
    {
        Vote aVote = votes.get(index);
        return aVote;
    }

    public List<Vote> getVotes()
    {
        List<Vote> newVotes = Collections.unmodifiableList(votes);
        return newVotes;
    }

    public int numberOfVotes()
    {
        int number = votes.size();
        return number;
    }

    public boolean hasVotes()
    {
        boolean has = votes.size() > 0;
        return has;
    }

    public int indexOfVote(Vote aVote)
    {
        int index = votes.indexOf(aVote);
        return index;
    }
    /* Code from template association_SetOneToMany */
    public boolean setExpo(Expo aExpo)
    {
        boolean wasSet = false;
        if (aExpo == null)
        {
            return wasSet;
        }

        Expo existingExpo = expo;
        expo = aExpo;
        if (existingExpo != null && !existingExpo.equals(aExpo))
        {
            existingExpo.removeStand(this);
        }
        expo.addStand(this);
        wasSet = true;
        return wasSet;
    }
    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfOwner()
    {
        return 0;
    }
    /* Code from template association_AddManyToManyMethod */
    public boolean addOwner(User aOwner)
    {
        boolean wasAdded = false;
        if (owner.contains(aOwner)) { return false; }
        owner.add(aOwner);
        if (aOwner.indexOfStand(this) != -1)
        {
            wasAdded = true;
        }
        else
        {
            wasAdded = aOwner.addStand(this);
            if (!wasAdded)
            {
                owner.remove(aOwner);
            }
        }
        return wasAdded;
    }
    /* Code from template association_RemoveMany */
    public boolean removeOwner(User aOwner)
    {
        boolean wasRemoved = false;
        if (!owner.contains(aOwner))
        {
            return wasRemoved;
        }

        int oldIndex = owner.indexOf(aOwner);
        owner.remove(oldIndex);
        if (aOwner.indexOfStand(this) == -1)
        {
            wasRemoved = true;
        }
        else
        {
            wasRemoved = aOwner.removeStand(this);
            if (!wasRemoved)
            {
                owner.add(oldIndex,aOwner);
            }
        }
        return wasRemoved;
    }
    /* Code from template association_AddIndexControlFunctions */
    public boolean addOwnerAt(User aOwner, int index)
    {
        boolean wasAdded = false;
        if(addOwner(aOwner))
        {
            if(index < 0 ) { index = 0; }
            if(index > numberOfOwner()) { index = numberOfOwner() - 1; }
            owner.remove(aOwner);
            owner.add(index, aOwner);
            wasAdded = true;
        }
        return wasAdded;
    }

    public boolean addOrMoveOwnerAt(User aOwner, int index)
    {
        boolean wasAdded = false;
        if(owner.contains(aOwner))
        {
            if(index < 0 ) { index = 0; }
            if(index > numberOfOwner()) { index = numberOfOwner() - 1; }
            owner.remove(aOwner);
            owner.add(index, aOwner);
            wasAdded = true;
        }
        else
        {
            wasAdded = addOwnerAt(aOwner, index);
        }
        return wasAdded;
    }
    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfVotes()
    {
        return 0;
    }
    /* Code from template association_AddManyToOne */
    public Vote addVote(int aScore, User aUser)
    {
        return new Vote(aScore, aUser, this);
    }

    public boolean addVote(Vote aVote)
    {
        boolean wasAdded = false;
        if (votes.contains(aVote)) { return false; }
        Stand existingStand = aVote.getStand();
        boolean isNewStand = existingStand != null && !this.equals(existingStand);
        if (isNewStand)
        {
            aVote.setStand(this);
        }
        else
        {
            votes.add(aVote);
        }
        wasAdded = true;
        return wasAdded;
    }

    public boolean removeVote(Vote aVote)
    {
        boolean wasRemoved = false;
        //Unable to remove aVote, as it must always have a stand
        if (!this.equals(aVote.getStand()))
        {
            votes.remove(aVote);
            wasRemoved = true;
        }
        return wasRemoved;
    }
    /* Code from template association_AddIndexControlFunctions */
    public boolean addVoteAt(Vote aVote, int index)
    {
        boolean wasAdded = false;
        if(addVote(aVote))
        {
            if(index < 0 ) { index = 0; }
            if(index > numberOfVotes()) { index = numberOfVotes() - 1; }
            votes.remove(aVote);
            votes.add(index, aVote);
            wasAdded = true;
        }
        return wasAdded;
    }

    public boolean addOrMoveVoteAt(Vote aVote, int index)
    {
        boolean wasAdded = false;
        if(votes.contains(aVote))
        {
            if(index < 0 ) { index = 0; }
            if(index > numberOfVotes()) { index = numberOfVotes() - 1; }
            votes.remove(aVote);
            votes.add(index, aVote);
            wasAdded = true;
        }
        else
        {
            wasAdded = addVoteAt(aVote, index);
        }
        return wasAdded;
    }

    public void delete()
    {
        Expo placeholderExpo = expo;
        this.expo = null;
        if(placeholderExpo != null)
        {
            placeholderExpo.removeStand(this);
        }
        ArrayList<User> copyOfOwner = new ArrayList<User>(owner);
        owner.clear();
        for(User aOwner : copyOfOwner)
        {
            aOwner.removeStand(this);
        }
        for(int i=votes.size(); i > 0; i--)
        {
            Vote aVote = votes.get(i - 1);
            aVote.delete();
        }
    }


    public String toString()
    {
        return super.toString() + "["+
                "standName" + ":" + getStandName()+ "]" + System.getProperties().getProperty("line.separator") +
                "  " + "expo = "+(getExpo()!=null?Integer.toHexString(System.identityHashCode(getExpo())):"null");
    }
}



//%% NEW FILE Vote BEGINS HERE %%

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4450.6749b7105 modeling language!*/



// line 22 "model.ump"
// line 48 "model.ump"
public class Vote
{

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Vote Attributes
    private int score;

    //Vote Associations
    private User user;
    private Stand stand;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Vote(int aScore, User aUser, Stand aStand)
    {
        score = aScore;
        boolean didAddUser = setUser(aUser);
        if (!didAddUser)
        {
            throw new RuntimeException("Unable to create vote due to user");
        }
        boolean didAddStand = setStand(aStand);
        if (!didAddStand)
        {
            throw new RuntimeException("Unable to create vote due to stand");
        }
    }

    //------------------------
    // INTERFACE
    //------------------------

    public boolean setScore(int aScore)
    {
        boolean wasSet = false;
        score = aScore;
        wasSet = true;
        return wasSet;
    }

    public int getScore()
    {
        return score;
    }
    /* Code from template association_GetOne */
    public User getUser()
    {
        return user;
    }
    /* Code from template association_GetOne */
    public Stand getStand()
    {
        return stand;
    }
    /* Code from template association_SetOneToMany */
    public boolean setUser(User aUser)
    {
        boolean wasSet = false;
        if (aUser == null)
        {
            return wasSet;
        }

        User existingUser = user;
        user = aUser;
        if (existingUser != null && !existingUser.equals(aUser))
        {
            existingUser.removeVote(this);
        }
        user.addVote(this);
        wasSet = true;
        return wasSet;
    }
    /* Code from template association_SetOneToMany */
    public boolean setStand(Stand aStand)
    {
        boolean wasSet = false;
        if (aStand == null)
        {
            return wasSet;
        }

        Stand existingStand = stand;
        stand = aStand;
        if (existingStand != null && !existingStand.equals(aStand))
        {
            existingStand.removeVote(this);
        }
        stand.addVote(this);
        wasSet = true;
        return wasSet;
    }

    public void delete()
    {
        User placeholderUser = user;
        this.user = null;
        if(placeholderUser != null)
        {
            placeholderUser.removeVote(this);
        }
        Stand placeholderStand = stand;
        this.stand = null;
        if(placeholderStand != null)
        {
            placeholderStand.removeVote(this);
        }
    }


    public String toString()
    {
        return super.toString() + "["+
                "score" + ":" + getScore()+ "]" + System.getProperties().getProperty("line.separator") +
                "  " + "user = "+(getUser()!=null?Integer.toHexString(System.identityHashCode(getUser())):"null") + System.getProperties().getProperty("line.separator") +
                "  " + "stand = "+(getStand()!=null?Integer.toHexString(System.identityHashCode(getStand())):"null");
    }
}



//%% NEW FILE Expo BEGINS HERE %%

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4450.6749b7105 modeling language!*/


import java.sql.Date;
        import java.util.*;

// line 2 "model.ump"
// line 31 "model.ump"
public class Expo
{

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Expo Attributes
    private String expoName;
    private Date startTime;
    private Date endTime;

    //Expo Associations
    private List<Stand> stands;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Expo(String aExpoName, Date aStartTime, Date aEndTime)
    {
        expoName = aExpoName;
        startTime = aStartTime;
        endTime = aEndTime;
        stands = new ArrayList<Stand>();
    }

    //------------------------
    // INTERFACE
    //------------------------

    public boolean setExpoName(String aExpoName)
    {
        boolean wasSet = false;
        expoName = aExpoName;
        wasSet = true;
        return wasSet;
    }

    public boolean setStartTime(Date aStartTime)
    {
        boolean wasSet = false;
        startTime = aStartTime;
        wasSet = true;
        return wasSet;
    }

    public boolean setEndTime(Date aEndTime)
    {
        boolean wasSet = false;
        endTime = aEndTime;
        wasSet = true;
        return wasSet;
    }

    public String getExpoName()
    {
        return expoName;
    }

    public Date getStartTime()
    {
        return startTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }
    /* Code from template association_GetMany */
    public Stand getStand(int index)
    {
        Stand aStand = stands.get(index);
        return aStand;
    }

    public List<Stand> getStands()
    {
        List<Stand> newStands = Collections.unmodifiableList(stands);
        return newStands;
    }

    public int numberOfStands()
    {
        int number = stands.size();
        return number;
    }

    public boolean hasStands()
    {
        boolean has = stands.size() > 0;
        return has;
    }

    public int indexOfStand(Stand aStand)
    {
        int index = stands.indexOf(aStand);
        return index;
    }
    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfStands()
    {
        return 0;
    }
    /* Code from template association_AddManyToOne */
    public Stand addStand(String aStandName)
    {
        return new Stand(aStandName, this);
    }

    public boolean addStand(Stand aStand)
    {
        boolean wasAdded = false;
        if (stands.contains(aStand)) { return false; }
        Expo existingExpo = aStand.getExpo();
        boolean isNewExpo = existingExpo != null && !this.equals(existingExpo);
        if (isNewExpo)
        {
            aStand.setExpo(this);
        }
        else
        {
            stands.add(aStand);
        }
        wasAdded = true;
        return wasAdded;
    }

    public boolean removeStand(Stand aStand)
    {
        boolean wasRemoved = false;
        //Unable to remove aStand, as it must always have a expo
        if (!this.equals(aStand.getExpo()))
        {
            stands.remove(aStand);
            wasRemoved = true;
        }
        return wasRemoved;
    }
    /* Code from template association_AddIndexControlFunctions */
    public boolean addStandAt(Stand aStand, int index)
    {
        boolean wasAdded = false;
        if(addStand(aStand))
        {
            if(index < 0 ) { index = 0; }
            if(index > numberOfStands()) { index = numberOfStands() - 1; }
            stands.remove(aStand);
            stands.add(index, aStand);
            wasAdded = true;
        }
        return wasAdded;
    }

    public boolean addOrMoveStandAt(Stand aStand, int index)
    {
        boolean wasAdded = false;
        if(stands.contains(aStand))
        {
            if(index < 0 ) { index = 0; }
            if(index > numberOfStands()) { index = numberOfStands() - 1; }
            stands.remove(aStand);
            stands.add(index, aStand);
            wasAdded = true;
        }
        else
        {
            wasAdded = addStandAt(aStand, index);
        }
        return wasAdded;
    }

    public void delete()
    {
        for(int i=stands.size(); i > 0; i--)
        {
            Stand aStand = stands.get(i - 1);
            aStand.delete();
        }
    }


    public String toString()
    {
        return super.toString() + "["+
                "expoName" + ":" + getExpoName()+ "]" + System.getProperties().getProperty("line.separator") +
                "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
                "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null");
    }
}



//%% NEW FILE User BEGINS HERE %%

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4450.6749b7105 modeling language!*/


import java.util.*;

// line 14 "model.ump"
// line 43 "model.ump"
public class User
{

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //User Attributes
    private String username;
    private int pin;
    private boolean isAdmin;
    private boolean isVerified;
    private boolean isJury;

    //User Associations
    private List<Stand> stands;
    private List<Vote> votes;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public User(String aUsername, int aPin, boolean aIsAdmin, boolean aIsVerified, boolean aIsJury)
    {
        username = aUsername;
        pin = aPin;
        isAdmin = aIsAdmin;
        isVerified = aIsVerified;
        isJury = aIsJury;
        stands = new ArrayList<Stand>();
        votes = new ArrayList<Vote>();
    }

    //------------------------
    // INTERFACE
    //------------------------

    public boolean setUsername(String aUsername)
    {
        boolean wasSet = false;
        username = aUsername;
        wasSet = true;
        return wasSet;
    }

    public boolean setPin(int aPin)
    {
        boolean wasSet = false;
        pin = aPin;
        wasSet = true;
        return wasSet;
    }

    public boolean setIsAdmin(boolean aIsAdmin)
    {
        boolean wasSet = false;
        isAdmin = aIsAdmin;
        wasSet = true;
        return wasSet;
    }

    public boolean setIsVerified(boolean aIsVerified)
    {
        boolean wasSet = false;
        isVerified = aIsVerified;
        wasSet = true;
        return wasSet;
    }

    public boolean setIsJury(boolean aIsJury)
    {
        boolean wasSet = false;
        isJury = aIsJury;
        wasSet = true;
        return wasSet;
    }

    public String getUsername()
    {
        return username;
    }

    public int getPin()
    {
        return pin;
    }

    public boolean getIsAdmin()
    {
        return isAdmin;
    }

    public boolean getIsVerified()
    {
        return isVerified;
    }

    public boolean getIsJury()
    {
        return isJury;
    }
    /* Code from template association_GetMany */
    public Stand getStand(int index)
    {
        Stand aStand = stands.get(index);
        return aStand;
    }

    public List<Stand> getStands()
    {
        List<Stand> newStands = Collections.unmodifiableList(stands);
        return newStands;
    }

    public int numberOfStands()
    {
        int number = stands.size();
        return number;
    }

    public boolean hasStands()
    {
        boolean has = stands.size() > 0;
        return has;
    }

    public int indexOfStand(Stand aStand)
    {
        int index = stands.indexOf(aStand);
        return index;
    }
    /* Code from template association_GetMany */
    public Vote getVote(int index)
    {
        Vote aVote = votes.get(index);
        return aVote;
    }

    public List<Vote> getVotes()
    {
        List<Vote> newVotes = Collections.unmodifiableList(votes);
        return newVotes;
    }

    public int numberOfVotes()
    {
        int number = votes.size();
        return number;
    }

    public boolean hasVotes()
    {
        boolean has = votes.size() > 0;
        return has;
    }

    public int indexOfVote(Vote aVote)
    {
        int index = votes.indexOf(aVote);
        return index;
    }
    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfStands()
    {
        return 0;
    }
    /* Code from template association_AddManyToManyMethod */
    public boolean addStand(Stand aStand)
    {
        boolean wasAdded = false;
        if (stands.contains(aStand)) { return false; }
        stands.add(aStand);
        if (aStand.indexOfOwner(this) != -1)
        {
            wasAdded = true;
        }
        else
        {
            wasAdded = aStand.addOwner(this);
            if (!wasAdded)
            {
                stands.remove(aStand);
            }
        }
        return wasAdded;
    }
    /* Code from template association_RemoveMany */
    public boolean removeStand(Stand aStand)
    {
        boolean wasRemoved = false;
        if (!stands.contains(aStand))
        {
            return wasRemoved;
        }

        int oldIndex = stands.indexOf(aStand);
        stands.remove(oldIndex);
        if (aStand.indexOfOwner(this) == -1)
        {
            wasRemoved = true;
        }
        else
        {
            wasRemoved = aStand.removeOwner(this);
            if (!wasRemoved)
            {
                stands.add(oldIndex,aStand);
            }
        }
        return wasRemoved;
    }
    /* Code from template association_AddIndexControlFunctions */
    public boolean addStandAt(Stand aStand, int index)
    {
        boolean wasAdded = false;
        if(addStand(aStand))
        {
            if(index < 0 ) { index = 0; }
            if(index > numberOfStands()) { index = numberOfStands() - 1; }
            stands.remove(aStand);
            stands.add(index, aStand);
            wasAdded = true;
        }
        return wasAdded;
    }

    public boolean addOrMoveStandAt(Stand aStand, int index)
    {
        boolean wasAdded = false;
        if(stands.contains(aStand))
        {
            if(index < 0 ) { index = 0; }
            if(index > numberOfStands()) { index = numberOfStands() - 1; }
            stands.remove(aStand);
            stands.add(index, aStand);
            wasAdded = true;
        }
        else
        {
            wasAdded = addStandAt(aStand, index);
        }
        return wasAdded;
    }
    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfVotes()
    {
        return 0;
    }
    /* Code from template association_AddManyToOne */
    public Vote addVote(int aScore, Stand aStand)
    {
        return new Vote(aScore, this, aStand);
    }

    public boolean addVote(Vote aVote)
    {
        boolean wasAdded = false;
        if (votes.contains(aVote)) { return false; }
        User existingUser = aVote.getUser();
        boolean isNewUser = existingUser != null && !this.equals(existingUser);
        if (isNewUser)
        {
            aVote.setUser(this);
        }
        else
        {
            votes.add(aVote);
        }
        wasAdded = true;
        return wasAdded;
    }

    public boolean removeVote(Vote aVote)
    {
        boolean wasRemoved = false;
        //Unable to remove aVote, as it must always have a user
        if (!this.equals(aVote.getUser()))
        {
            votes.remove(aVote);
            wasRemoved = true;
        }
        return wasRemoved;
    }
    /* Code from template association_AddIndexControlFunctions */
    public boolean addVoteAt(Vote aVote, int index)
    {
        boolean wasAdded = false;
        if(addVote(aVote))
        {
            if(index < 0 ) { index = 0; }
            if(index > numberOfVotes()) { index = numberOfVotes() - 1; }
            votes.remove(aVote);
            votes.add(index, aVote);
            wasAdded = true;
        }
        return wasAdded;
    }

    public boolean addOrMoveVoteAt(Vote aVote, int index)
    {
        boolean wasAdded = false;
        if(votes.contains(aVote))
        {
            if(index < 0 ) { index = 0; }
            if(index > numberOfVotes()) { index = numberOfVotes() - 1; }
            votes.remove(aVote);
            votes.add(index, aVote);
            wasAdded = true;
        }
        else
        {
            wasAdded = addVoteAt(aVote, index);
        }
        return wasAdded;
    }

    public void delete()
    {
        ArrayList<Stand> copyOfStands = new ArrayList<Stand>(stands);
        stands.clear();
        for(Stand aStand : copyOfStands)
        {
            aStand.removeOwner(this);
        }
        for(int i=votes.size(); i > 0; i--)
        {
            Vote aVote = votes.get(i - 1);
            aVote.delete();
        }
    }


    public String toString()
    {
        return super.toString() + "["+
                "username" + ":" + getUsername()+ "," +
                "pin" + ":" + getPin()+ "," +
                "isAdmin" + ":" + getIsAdmin()+ "," +
                "isVerified" + ":" + getIsVerified()+ "," +
                "isJury" + ":" + getIsJury()+ "]";
    }
}