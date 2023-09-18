import {useParams} from "react-router-dom";

import {useEffect, useState} from "react";

import {fetchTherapyByPage} from "../../services/therapyService";

import Loading from "../Loading/Loading";
import Error from "../Error/Error";

import "./Therapy.css";

const Therapy = () => {
    const {page} = useParams();
    const [therapy, setTherapy] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchTherapy = async () => {
            try {
                // Delaying the loading of therapy details by 5 seconds to demonstrate the loading animation
                await new Promise(resolve => setTimeout(resolve, 5000));

                const therapy = await fetchTherapyByPage(page);
                setTherapy(therapy);
            } catch (error) {
                console.log(error);
                setError("Failed to fetch therapy data details");
            } finally {
                setLoading(false);
            }
        }
        fetchTherapy();
    }, [page]);

    if (loading) {
        return <Loading />
    }

    if (error) {
        return <Error message={error} />
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