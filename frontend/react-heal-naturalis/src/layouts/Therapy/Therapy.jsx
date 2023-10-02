import {useParams} from "react-router-dom";
import {useFetchTherapy} from "../../hooks/useFetchTherapy";

import Loading from "../Loading/Loading";
import Error from "../Error/Error";

import "./Therapy.css";

const Therapy = () => {
    const {page} = useParams();
    const {therapy, loading, errorData} = useFetchTherapy(page);

    if (loading) {
        return <Loading/>
    }

    if (errorData) {
        return <Error errorData={errorData}/>
    }

    return (

        <div className="container">
            <div className="row">
                <div className="col-12">
                    <h1 className="therapy-name">{therapy?.name}</h1>
                    <img className="therapy-image" src={therapy?.imageUrl} alt={therapy?.name}/>
                    <p className="therapy-description">{therapy?.description}</p>
                    <h2 className="therapist-name">Therapist name: {therapy?.therapistName}</h2>
                    <p className="therapy-schedule">Weekly schedule: {therapy?.schedule}</p>
                    <p className="therapy-phone">Reserve your session on phone: {therapy?.phoneNumber}</p>
                    <p className="therapy-price">Price per session: {therapy?.price} EUR</p>
                </div>
            </div>
        </div>

    )
}

export default Therapy;