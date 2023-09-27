import {useParams} from "react-router-dom";

import {useEffect, useState} from "react";

import {fetchSingleTherapyByPageIndex} from "../../services/therapyService";
import {createErrorDataObject} from "../../services/errorService";
import {createDelay} from "../../services/fetchService";

import Loading from "../Loading/Loading";
import Error from "../Error/Error";

import {FETCH_DELAY_DURATION} from "../../utils/constants";

import "./Therapy.css";

const Therapy = () => {
    const {page} = useParams();
    const [therapy, setTherapy] = useState(null);
    const [loading, setLoading] = useState(true);
    const [errorData, setErrorData] = useState(null);

    useEffect(() => {
        const fetchTherapy = async () => {
            try {
                // Delay the fetch to demonstrate the loading animation
                await createDelay(FETCH_DELAY_DURATION);

                const therapy = await fetchSingleTherapyByPageIndex(page);
                setTherapy(therapy);
            } catch (error) {
                console.error("Error captured while fetching therapy: ",error);

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