const person = {
    name: 'Mara',
    address: {
        line1:'Baker Street',
        city:"London",
        country:"UK"
    },
    profiles : [
        'twitter',
        'linkedin',
        'instagram'
    ],
    printProfile: () => {
        person.profiles.map(
            (profile) => {
                console.log(profile); //profile => console.log(profile) 이렇게 간단하게 써도 된다.
            } 
        )    
    }
}


export default function LearningJavaScript(){
    return (
    <div>
        <div>{person.name}</div>   
        <div>{person.address.line1}</div>
        <div>{person.profiles[0]}</div>
        <div>{person.printProfile()}</div>
    </div>
    )
}