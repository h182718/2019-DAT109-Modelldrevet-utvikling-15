//%% NEW FILE Expo BEGINS HERE %%


/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4450.6749b7105 modeling language!*/

class Expo
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Expo State Machines
  private static $StatusExpo = 1;
  private static $StatusLoggedIn = 2;
  private static $StatusSignup = 3;
  private static $StatusScan = 4;
  private static $StatusControlpanel = 5;
  private static $StatusAddStand = 6;
  private static $StatusAddExpo = 7;
  private static $StatusVote = 8;
  private static $StatusStand = 9;
  private $status;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct()
  {
    $this->setStatus(self::$StatusExpo);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getStatusFullName()
  {
    $answer = $this->getStatus();
    return $answer;
  }

  public function getStatus()
  {
    if ($this->status == self::$StatusExpo) { return "StatusExpo"; }
    elseif ($this->status == self::$StatusLoggedIn) { return "StatusLoggedIn"; }
    elseif ($this->status == self::$StatusSignup) { return "StatusSignup"; }
    elseif ($this->status == self::$StatusScan) { return "StatusScan"; }
    elseif ($this->status == self::$StatusControlpanel) { return "StatusControlpanel"; }
    elseif ($this->status == self::$StatusAddStand) { return "StatusAddStand"; }
    elseif ($this->status == self::$StatusAddExpo) { return "StatusAddExpo"; }
    elseif ($this->status == self::$StatusVote) { return "StatusVote"; }
    elseif ($this->status == self::$StatusStand) { return "StatusStand"; }
    return null;
  }

  public function LoggingIn()
  {
    $wasEventProcessed = false;

    $aStatus = $this->status;
    if ($aStatus == self::$StatusExpo)
    {
      $this->setStatus(self::$StatusLoggedIn);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function IfUser()
  {
    $wasEventProcessed = false;

    $aStatus = $this->status;
    if ($aStatus == self::$StatusLoggedIn)
    {
      $this->setStatus(self::$StatusScan);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function IfAdmin()
  {
    $wasEventProcessed = false;

    $aStatus = $this->status;
    if ($aStatus == self::$StatusLoggedIn)
    {
      $this->setStatus(self::$StatusControlpanel);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function ScanQR()
  {
    $wasEventProcessed = false;

    $aStatus = $this->status;
    if ($aStatus == self::$StatusScan)
    {
      $this->setStatus(self::$StatusStand);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function AddStand()
  {
    $wasEventProcessed = false;

    $aStatus = $this->status;
    if ($aStatus == self::$StatusControlpanel)
    {
      $this->setStatus(self::$StatusAddStand);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function AddExpo()
  {
    $wasEventProcessed = false;

    $aStatus = $this->status;
    if ($aStatus == self::$StatusControlpanel)
    {
      $this->setStatus(self::$StatusAddExpo);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  private function __autotransition1383__()
  {
    $wasEventProcessed = false;

    $aStatus = $this->status;
    if ($aStatus == self::$StatusVote)
    {
      $this->setStatus(self::$StatusScan);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function Score0-5()
  {
    $wasEventProcessed = false;

    $aStatus = $this->status;
    if ($aStatus == self::$StatusStand)
    {
      $this->setStatus(self::$StatusVote);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  private function setStatus($aStatus)
  {
    $this->status = $aStatus;

    // entry actions and do activities
    if ($this->status == self::$StatusVote)
    {
      $this->__autotransition1383__();
    }
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {}

}