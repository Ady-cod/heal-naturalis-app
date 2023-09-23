import {useParams} from "react-router-dom";

import {useEffect, useState} from "react";

import {fetchSingleTherapyByPageIndex} from "../../services/therapyService";
import {createErrorDataObject} from "../../services/errorService";

import Loading from "../Loading/Loading";
import Error from "../Error/Error";

import {LOADING_DELAY_DURATION} from "../../utils/constants";

import "./Therapy.css";

const Therapy = () => {
    const {page} = useParams();
    const [therapy, setTherapy] = useState(null);
    const [loading, setLoading] = useState(true);
    const [errorData, setErrorData] = useState(null);

    useEffect(() => {
        const fetchTherapy = async () => {
            try {
                // Delaying the loading of therapy details by 5 seconds to demonstrate the loading animation
                await new Promise(resolve => setTimeout(resolve, LOADING_DELAY_DURATION));

                const therapy = await fetchSingleTherapyByPageIndex(page);
                setTherapy(therapy);
            } catch (error) {
                console.log(error);

                const errorDataObject = createErrorDataObject(error);
                setErrorData(errorDataObject);
            } finally {
                setLoading(false);
            }
        }
        fetchTherapy();
    }, [page]);

    if (loading) {
        return <Loading />
    }

    if (errorData) {
        return <Error errorData={errorData} />
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