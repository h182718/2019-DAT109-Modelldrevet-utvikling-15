//%% NEW FILE Stand BEGINS HERE %%


/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4450.6749b7105 modeling language!*/

class Stand
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Stand Attributes
  private $standName;

  //Stand Associations
  private $expo;
  private $owner;
  private $votes;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aStandName, $aExpo)
  {
    $this->standName = $aStandName;
    $didAddExpo = $this->setExpo($aExpo);
    if (!$didAddExpo)
    {
      throw new Exception("Unable to create stand due to expo");
    }
    $this->owner = array();
    $this->votes = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setStandName($aStandName)
  {
    $wasSet = false;
    $this->standName = $aStandName;
    $wasSet = true;
    return $wasSet;
  }

  public function getStandName()
  {
    return $this->standName;
  }

  public function getExpo()
  {
    return $this->expo;
  }

  public function getOwner_index($index)
  {
    $aOwner = $this->owner[$index];
    return $aOwner;
  }

  public function getOwner()
  {
    $newOwner = $this->owner;
    return $newOwner;
  }

  public function numberOfOwner()
  {
    $number = count($this->owner);
    return $number;
  }

  public function hasOwner()
  {
    $has = $this->numberOfOwner() > 0;
    return $has;
  }

  public function indexOfOwner($aOwner)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->owner as $owner)
    {
      if ($owner->equals($aOwner))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getVote_index($index)
  {
    $aVote = $this->votes[$index];
    return $aVote;
  }

  public function getVotes()
  {
    $newVotes = $this->votes;
    return $newVotes;
  }

  public function numberOfVotes()
  {
    $number = count($this->votes);
    return $number;
  }

  public function hasVotes()
  {
    $has = $this->numberOfVotes() > 0;
    return $has;
  }

  public function indexOfVote($aVote)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->votes as $vote)
    {
      if ($vote->equals($aVote))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function setExpo($aExpo)
  {
    $wasSet = false;
    if ($aExpo == null)
    {
      return $wasSet;
    }

    $existingExpo = $this->expo;
    $this->expo = $aExpo;
    if ($existingExpo != null && $existingExpo != $aExpo)
    {
      $existingExpo->removeStand($this);
    }
    $this->expo->addStand($this);
    $wasSet = true;
    return $wasSet;
  }

  public static function minimumNumberOfOwner()
  {
    return 0;
  }

  public function addOwner($aOwner)
  {
    $wasAdded = false;
    if ($this->indexOfOwner($aOwner) !== -1) { return false; }
    $this->owner[] = $aOwner;
    if ($aOwner->indexOfStand($this) != -1)
    {
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $aOwner->addStand($this);
      if (!$wasAdded)
      {
        array_pop($this->owner);
      }
    }
    return $wasAdded;
  }

  public function removeOwner($aOwner)
  {
    $wasRemoved = false;
    if ($this->indexOfOwner($aOwner) == -1)
    {
      return $wasRemoved;
    }

    $oldIndex = $this->indexOfOwner($aOwner);
    unset($this->owner[$oldIndex]);
    if ($aOwner->indexOfStand($this) == -1)
    {
      $wasRemoved = true;
    }
    else
    {
      $wasRemoved = $aOwner->removeStand($this);
      if (!$wasRemoved)
      {
        $this->owner[$oldIndex] = $aOwner;
        ksort($this->owner);
      }
    }
    $this->owner = array_values($this->owner);
    return $wasRemoved;
  }

  public function addOwnerAt($aOwner, $index)
  {
    $wasAdded = false;
    if($this->addOwner($aOwner))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfOwner()) { $index = $this->numberOfOwner() - 1; }
      array_splice($this->owner, $this->indexOfOwner($aOwner), 1);
      array_splice($this->owner, $index, 0, array($aOwner));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveOwnerAt($aOwner, $index)
  {
    $wasAdded = false;
    if($this->indexOfOwner($aOwner) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfOwner()) { $index = $this->numberOfOwner() - 1; }
      array_splice($this->owner, $this->indexOfOwner($aOwner), 1);
      array_splice($this->owner, $index, 0, array($aOwner));
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $this->addOwnerAt($aOwner, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfVotes()
  {
    return 0;
  }

  public function addVoteVia($aScore, $aUser)
  {
    return new Vote($aScore, $aUser, $this);
  }

  public function addVote($aVote)
  {
    $wasAdded = false;
    if ($this->indexOfVote($aVote) !== -1) { return false; }
    $existingStand = $aVote->getStand();
    $isNewStand = $existingStand != null && $this !== $existingStand;
    if ($isNewStand)
    {
      $aVote->setStand($this);
    }
    else
    {
      $this->votes[] = $aVote;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeVote($aVote)
  {
    $wasRemoved = false;
    //Unable to remove aVote, as it must always have a stand
    if ($this !== $aVote->getStand())
    {
      unset($this->votes[$this->indexOfVote($aVote)]);
      $this->votes = array_values($this->votes);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addVoteAt($aVote, $index)
  {
    $wasAdded = false;
    if($this->addVote($aVote))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfVotes()) { $index = $this->numberOfVotes() - 1; }
      array_splice($this->votes, $this->indexOfVote($aVote), 1);
      array_splice($this->votes, $index, 0, array($aVote));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveVoteAt($aVote, $index)
  {
    $wasAdded = false;
    if($this->indexOfVote($aVote) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfVotes()) { $index = $this->numberOfVotes() - 1; }
      array_splice($this->votes, $this->indexOfVote($aVote), 1);
      array_splice($this->votes, $index, 0, array($aVote));
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $this->addVoteAt($aVote, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $placeholderExpo = $this->expo;
    $this->expo = null;
    $placeholderExpo->removeStand($this);
    $copyOfOwner = $this->owner;
    $this->owner = array();
    foreach ($copyOfOwner as $aOwner)
    {
      $aOwner->removeStand($this);
    }
    foreach ($this->votes as $aVote)
    {
      $aVote->delete();
    }
  }

}




//%% NEW FILE Vote BEGINS HERE %%


/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4450.6749b7105 modeling language!*/

class Vote
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Vote Attributes
  private $score;

  //Vote Associations
  private $user;
  private $stand;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aScore, $aUser, $aStand)
  {
    $this->score = $aScore;
    $didAddUser = $this->setUser($aUser);
    if (!$didAddUser)
    {
      throw new Exception("Unable to create vote due to user");
    }
    $didAddStand = $this->setStand($aStand);
    if (!$didAddStand)
    {
      throw new Exception("Unable to create vote due to stand");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setScore($aScore)
  {
    $wasSet = false;
    $this->score = $aScore;
    $wasSet = true;
    return $wasSet;
  }

  public function getScore()
  {
    return $this->score;
  }

  public function getUser()
  {
    return $this->user;
  }

  public function getStand()
  {
    return $this->stand;
  }

  public function setUser($aUser)
  {
    $wasSet = false;
    if ($aUser == null)
    {
      return $wasSet;
    }

    $existingUser = $this->user;
    $this->user = $aUser;
    if ($existingUser != null && $existingUser != $aUser)
    {
      $existingUser->removeVote($this);
    }
    $this->user->addVote($this);
    $wasSet = true;
    return $wasSet;
  }

  public function setStand($aStand)
  {
    $wasSet = false;
    if ($aStand == null)
    {
      return $wasSet;
    }

    $existingStand = $this->stand;
    $this->stand = $aStand;
    if ($existingStand != null && $existingStand != $aStand)
    {
      $existingStand->removeVote($this);
    }
    $this->stand->addVote($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $placeholderUser = $this->user;
    $this->user = null;
    $placeholderUser->removeVote($this);
    $placeholderStand = $this->stand;
    $this->stand = null;
    $placeholderStand->removeVote($this);
  }

}




//%% NEW FILE Expo BEGINS HERE %%


/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4450.6749b7105 modeling language!*/

class Expo
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Expo Attributes
  private $expoName;
  private $startTime;
  private $endTime;

  //Expo Associations
  private $stands;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aExpoName, $aStartTime, $aEndTime)
  {
    $this->expoName = $aExpoName;
    $this->startTime = $aStartTime;
    $this->endTime = $aEndTime;
    $this->stands = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setExpoName($aExpoName)
  {
    $wasSet = false;
    $this->expoName = $aExpoName;
    $wasSet = true;
    return $wasSet;
  }

  public function setStartTime($aStartTime)
  {
    $wasSet = false;
    $this->startTime = $aStartTime;
    $wasSet = true;
    return $wasSet;
  }

  public function setEndTime($aEndTime)
  {
    $wasSet = false;
    $this->endTime = $aEndTime;
    $wasSet = true;
    return $wasSet;
  }

  public function getExpoName()
  {
    return $this->expoName;
  }

  public function getStartTime()
  {
    return $this->startTime;
  }

  public function getEndTime()
  {
    return $this->endTime;
  }

  public function getStand_index($index)
  {
    $aStand = $this->stands[$index];
    return $aStand;
  }

  public function getStands()
  {
    $newStands = $this->stands;
    return $newStands;
  }

  public function numberOfStands()
  {
    $number = count($this->stands);
    return $number;
  }

  public function hasStands()
  {
    $has = $this->numberOfStands() > 0;
    return $has;
  }

  public function indexOfStand($aStand)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->stands as $stand)
    {
      if ($stand->equals($aStand))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfStands()
  {
    return 0;
  }

  public function addStandVia($aStandName)
  {
    return new Stand($aStandName, $this);
  }

  public function addStand($aStand)
  {
    $wasAdded = false;
    if ($this->indexOfStand($aStand) !== -1) { return false; }
    $existingExpo = $aStand->getExpo();
    $isNewExpo = $existingExpo != null && $this !== $existingExpo;
    if ($isNewExpo)
    {
      $aStand->setExpo($this);
    }
    else
    {
      $this->stands[] = $aStand;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeStand($aStand)
  {
    $wasRemoved = false;
    //Unable to remove aStand, as it must always have a expo
    if ($this !== $aStand->getExpo())
    {
      unset($this->stands[$this->indexOfStand($aStand)]);
      $this->stands = array_values($this->stands);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addStandAt($aStand, $index)
  {
    $wasAdded = false;
    if($this->addStand($aStand))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfStands()) { $index = $this->numberOfStands() - 1; }
      array_splice($this->stands, $this->indexOfStand($aStand), 1);
      array_splice($this->stands, $index, 0, array($aStand));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveStandAt($aStand, $index)
  {
    $wasAdded = false;
    if($this->indexOfStand($aStand) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfStands()) { $index = $this->numberOfStands() - 1; }
      array_splice($this->stands, $this->indexOfStand($aStand), 1);
      array_splice($this->stands, $index, 0, array($aStand));
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $this->addStandAt($aStand, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    foreach ($this->stands as $aStand)
    {
      $aStand->delete();
    }
  }

}




//%% NEW FILE User BEGINS HERE %%


/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4450.6749b7105 modeling language!*/

class User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private $username;
  private $pin;
  private $isAdmin;
  private $isVerified;
  private $isJury;

  //User Associations
  private $stands;
  private $votes;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aUsername, $aPin, $aIsAdmin, $aIsVerified, $aIsJury)
  {
    $this->username = $aUsername;
    $this->pin = $aPin;
    $this->isAdmin = $aIsAdmin;
    $this->isVerified = $aIsVerified;
    $this->isJury = $aIsJury;
    $this->stands = array();
    $this->votes = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setUsername($aUsername)
  {
    $wasSet = false;
    $this->username = $aUsername;
    $wasSet = true;
    return $wasSet;
  }

  public function setPin($aPin)
  {
    $wasSet = false;
    $this->pin = $aPin;
    $wasSet = true;
    return $wasSet;
  }

  public function setIsAdmin($aIsAdmin)
  {
    $wasSet = false;
    $this->isAdmin = $aIsAdmin;
    $wasSet = true;
    return $wasSet;
  }

  public function setIsVerified($aIsVerified)
  {
    $wasSet = false;
    $this->isVerified = $aIsVerified;
    $wasSet = true;
    return $wasSet;
  }

  public function setIsJury($aIsJury)
  {
    $wasSet = false;
    $this->isJury = $aIsJury;
    $wasSet = true;
    return $wasSet;
  }

  public function getUsername()
  {
    return $this->username;
  }

  public function getPin()
  {
    return $this->pin;
  }

  public function getIsAdmin()
  {
    return $this->isAdmin;
  }

  public function getIsVerified()
  {
    return $this->isVerified;
  }

  public function getIsJury()
  {
    return $this->isJury;
  }

  public function getStand_index($index)
  {
    $aStand = $this->stands[$index];
    return $aStand;
  }

  public function getStands()
  {
    $newStands = $this->stands;
    return $newStands;
  }

  public function numberOfStands()
  {
    $number = count($this->stands);
    return $number;
  }

  public function hasStands()
  {
    $has = $this->numberOfStands() > 0;
    return $has;
  }

  public function indexOfStand($aStand)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->stands as $stand)
    {
      if ($stand->equals($aStand))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getVote_index($index)
  {
    $aVote = $this->votes[$index];
    return $aVote;
  }

  public function getVotes()
  {
    $newVotes = $this->votes;
    return $newVotes;
  }

  public function numberOfVotes()
  {
    $number = count($this->votes);
    return $number;
  }

  public function hasVotes()
  {
    $has = $this->numberOfVotes() > 0;
    return $has;
  }

  public function indexOfVote($aVote)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->votes as $vote)
    {
      if ($vote->equals($aVote))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfStands()
  {
    return 0;
  }

  public function addStand($aStand)
  {
    $wasAdded = false;
    if ($this->indexOfStand($aStand) !== -1) { return false; }
    $this->stands[] = $aStand;
    if ($aStand->indexOfOwner($this) != -1)
    {
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $aStand->addOwner($this);
      if (!$wasAdded)
      {
        array_pop($this->stands);
      }
    }
    return $wasAdded;
  }

  public function removeStand($aStand)
  {
    $wasRemoved = false;
    if ($this->indexOfStand($aStand) == -1)
    {
      return $wasRemoved;
    }

    $oldIndex = $this->indexOfStand($aStand);
    unset($this->stands[$oldIndex]);
    if ($aStand->indexOfOwner($this) == -1)
    {
      $wasRemoved = true;
    }
    else
    {
      $wasRemoved = $aStand->removeOwner($this);
      if (!$wasRemoved)
      {
        $this->stands[$oldIndex] = $aStand;
        ksort($this->stands);
      }
    }
    $this->stands = array_values($this->stands);
    return $wasRemoved;
  }

  public function addStandAt($aStand, $index)
  {
    $wasAdded = false;
    if($this->addStand($aStand))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfStands()) { $index = $this->numberOfStands() - 1; }
      array_splice($this->stands, $this->indexOfStand($aStand), 1);
      array_splice($this->stands, $index, 0, array($aStand));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveStandAt($aStand, $index)
  {
    $wasAdded = false;
    if($this->indexOfStand($aStand) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfStands()) { $index = $this->numberOfStands() - 1; }
      array_splice($this->stands, $this->indexOfStand($aStand), 1);
      array_splice($this->stands, $index, 0, array($aStand));
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $this->addStandAt($aStand, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfVotes()
  {
    return 0;
  }

  public function addVoteVia($aScore, $aStand)
  {
    return new Vote($aScore, $this, $aStand);
  }

  public function addVote($aVote)
  {
    $wasAdded = false;
    if ($this->indexOfVote($aVote) !== -1) { return false; }
    $existingUser = $aVote->getUser();
    $isNewUser = $existingUser != null && $this !== $existingUser;
    if ($isNewUser)
    {
      $aVote->setUser($this);
    }
    else
    {
      $this->votes[] = $aVote;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeVote($aVote)
  {
    $wasRemoved = false;
    //Unable to remove aVote, as it must always have a user
    if ($this !== $aVote->getUser())
    {
      unset($this->votes[$this->indexOfVote($aVote)]);
      $this->votes = array_values($this->votes);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addVoteAt($aVote, $index)
  {
    $wasAdded = false;
    if($this->addVote($aVote))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfVotes()) { $index = $this->numberOfVotes() - 1; }
      array_splice($this->votes, $this->indexOfVote($aVote), 1);
      array_splice($this->votes, $index, 0, array($aVote));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveVoteAt($aVote, $index)
  {
    $wasAdded = false;
    if($this->indexOfVote($aVote) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfVotes()) { $index = $this->numberOfVotes() - 1; }
      array_splice($this->votes, $this->indexOfVote($aVote), 1);
      array_splice($this->votes, $index, 0, array($aVote));
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $this->addVoteAt($aVote, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $copyOfStands = $this->stands;
    $this->stands = array();
    foreach ($copyOfStands as $aStand)
    {
      $aStand->removeOwner($this);
    }
    foreach ($this->votes as $aVote)
    {
      $aVote->delete();
    }
  }

}