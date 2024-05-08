import {useParams} from "react-router-dom";

import {useFetchItemById} from "../../hooks/useFetchItemById";

import Loading from "../Loading/Loading";
import Error from "../Error/Error";

import {THERAPY_BASE_URL} from "../../utils/constants";

import "./Therapy.css";

const Therapy = () => {
    const {therapyId} = useParams();
    const {item:therapy, isLoading, errorData} = useFetchItemById(THERAPY_BASE_URL, therapyId);

    if (isLoading) {
        return <Loading/>
    }

    if (errorData) {
        return <Error errorData={errorData}/>
    }

    return (

        <div className="container">
            <h1 className="therapy-name">{therapy?.name}</h1>
            <div className="row mb-3">
                <div className="col-12">
                    <img className="therapy-image float-start me-3" src={therapy?.imageUrl} alt={therapy?.name}/>
                    <p className="therapy-description">{therapy?.description}</p>
                </div>
            </div>
            <div className="card p-3 ">
                <div className="row">
                    <div className="col-12 col-sm-6 d-flex justify-content-center justify-content-sm-start">
                        <h4 className="therapist-name">
                            <img className="m-1" src="https://heal-naturalis-bucket.s3.eu-central-1.amazonaws.com/icons/therapist.svg"/>
                            {therapy?.therapistName}
                        </h4>
                    </div>
                    <div className="col-12 col-sm-6 d-flex justify-content-center justify-content-sm-end">
                    <span className="therapy-phone">
                        <a className="btn btn-success my-auto p-0 pe-2" href={`tel:${therapy?.phoneNumber}`}>
                            <img className="m-1" src="https://heal-naturalis-bucket.s3.eu-central-1.amazonaws.com/icons/phone.svg"/>
                            {therapy?.phoneNumber}
                        </a>
                    </span>
                    </div>
                    <div className="col-12 col-sm-6 d-flex justify-content-center justify-content-sm-start">
                    <span className="therapy-schedule">
                        <img className="m-1" src="https://heal-naturalis-bucket.s3.eu-central-1.amazonaws.com/icons/schedule.svg"/>
                        {therapy?.schedule}
                    </span>
                    </div>
                    <div className="col-12 col-sm-6 d-flex justify-content-center justify-content-sm-end">
                        <p className="therapy-price">
                            <img className="m-1" src="https://heal-naturalis-bucket.s3.eu-central-1.amazonaws.com/icons/euro.svg"/>
                            {therapy?.price} / session
                        </p>
                    </div>
                </div>
            </div>
        </div>

    )
}

export default Therapy;