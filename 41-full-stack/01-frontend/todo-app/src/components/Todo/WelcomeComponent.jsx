import {Link, useParams} from 'react-router-dom';

export default function WelcomeComponent(){

    const {username} = useParams()

    // const params = useParams()
    // console.log(params.username) //위와 같은 뜻임

    return(
        <div>
            <h1>Welcome {username}</h1>
            <div className="Welcome">
                Manage Your todos. <Link to='/todos'>Go Here</Link>
            </div>
        </div>
    )
}