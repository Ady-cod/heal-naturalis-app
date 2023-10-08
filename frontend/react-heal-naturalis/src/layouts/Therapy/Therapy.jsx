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
            <h1 className="therapy-name">{therapy?.name}</h1>
            <div className="row">
                <div className="col-12 col-md-6">
                    <img className="therapy-image" src={therapy?.imageUrl} alt={therapy?.name}/>
                </div>
                <div className="col-12 col-md-6">
                    <p className="therapy-description">{therapy?.description}</p>
                </div>
            </div>
            <div className="row">
                <div className="col-12 col-md-6">
                    <h4 className="therapist-name">Therapist name: {therapy?.therapistName}</h4>
                </div>
                <div className="col-12 col-md-6 d-flex">
                    <p className="therapy-phone ms-auto">Reserve your session on phone: {therapy?.phoneNumber}</p>
                </div>
            </div>
            <div className="row">
                <div className="col-12 col-md-6">
                    <p className="therapy-schedule">Weekly schedule: {therapy?.schedule}</p>
                </div>
                <div className="col-12 col-md-6 d-flex">
                    <p className="therapy-price ms-auto">Price per session: {therapy?.price} EUR</p>
                </div>
            </div>
        </div>

    )
}

export default Therapy;