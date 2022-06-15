import { StyleSheet, Text, View, Button } from 'react-native';
import DeliveryList from '../../components/DeliveryList';
import React, { useEffect, useState } from "react";


const AvailableDeliveriesScreen = ({navigation}) => {

    const [fetchError, setFetchError] = useState(false);
    const [deliveries, setDeliveries] = useState([]);
    const [hasDeliveries, setHasDeliveries] = useState(true);
    

    // temporary fix for development, remove in production
    const process = {env: {REACT_APP_API_URL: "http://localhost:8080/api"}}

    useEffect(()=>{
        fetch(
            `${process.env.REACT_APP_API_URL}/purchases/requestedPurchase`,
            {
                headers: {'Access-Control-Allow-Origin': '*'}
            }
        )
        .then(response => response.json())
        .then(data => {
            setDeliveries(data)
            if (hasDeliveries===0){
              setHasDeliveries(false);
            }
        })
        .catch((reason) => {
            console.log(reason)
            setFetchError(true)
        })
    }, [])
    
    
    return(
      hasDeliveries?
      <>
      <View style={styles.container}>
        <Text style={styles.title}>Available Deliveries</Text>
        
        <View style={styles.list}>
          {
            fetchError ?
            <Text>An error ocurred fetching data</Text>
            :
            deliveries.length == 0 ?
            <Text>Fetching deliveries...</Text>
            :
            <DeliveryList items={deliveries} />
          }
        </View>
        
      </View>
      </>
      :
      <>
        <View style={styles.container}>
          <Text style={styles.title}>No Available Deliveries</Text>
        </View>
      </>
    )
};

const styles = StyleSheet.create({
    title: {
      fontSize: 20
    },
    container: {
        flex: 1,
        padding: 16,
        paddingTop: 48,
        backgroundColor: '#fff',
        alignItems: 'center',
        justifyContent: 'center',
    },
    list: {
      flex: 1,
      width: "80%"
    }
});

export default AvailableDeliveriesScreen;