class ObservableVM252Machine extends SimpleObservable
{
    private short myACC;
    private short myPC;
    private byte [] myMemory;
    private String myNextInstruction;
    private String [] myDisplayContents;

    //
    // Accessors
    //
    public short getACCValue()
    {
        return myACC;
    }

    public short getPCValue()
    {
        return myPC;
    }

    public byte [] getMemoryValue()
    {
        return myMemory;
    }

    public String getNextInstruction()
    {
        return myNextInstruction;
    }

    public String [] getDisplayContents()
    {
        return myDisplayContents;
    }

    //
    // Mutators
    //

    public void setACCValue(short other)
    {
        myACC = other;
        announceChange();
    }

    public void setPCValue(short other)
    {
        myPC = other;
        announceChange();
    }

    public void setMemoryValue(byte [] other)
    {
        myMemory = other;
        announceChange();
    }

    public void setNextInstruction(String other)
    {
        myNextInstruction = other;
        announceChange();
    }

    public void setDisplayContents(String [] other)
    {
        myDisplayContents = other;
    }

    //
    // Ctors
    //

    ObservableVM252Machine()
    {
        super();

        String [] welcomeContents = {"Welcome to VM252 debuger"};

        setACCValue((short)0);
        setPCValue((short)0);
        setMemoryValue(new byte [8192]);
        setNextInstruction("");
        setDisplayContents(welcomeContents);
    }

    ObservableVM252Machine(short initialACCValue, short initialPCValue, byte [] initialMemoryValue, String initialNextInstruction, String []initialDisplayContents)
    {
        super();

        setACCValue(initialACCValue);
        setPCValue(initialPCValue);
        setMemoryValue(initialMemoryValue);
        setNextInstruction(initialNextInstruction);
        setDisplayContents(initialDisplayContents);
    }
}
