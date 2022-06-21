import React, { useEffect, useState } from "react";
import { StyleSheet, Text, View, Button } from 'react-native';
import DeliveryList from '../../components/DeliveryList';
import { useFocusEffect } from '@react-navigation/native';
import AsyncStorage from '@react-native-async-storage/async-storage';


const AvailableDeliveriesScreen = ({navigation}) => {

    const [fetchError, setFetchError] = useState(false);
    const [deliveries, setDeliveries] = useState(null);
    const [riderID, setRiderId] = useState(null);
    

    // temporary fix for development, remove in production
    const process = {env: {REACT_APP_API_URL: "http://localhost:8080/api"}}

    useFocusEffect(
      React.useCallback(() => {
        setDeliveries(null)

        async function getCurrentRiderId() {
          let rider = await AsyncStorage.getItem("current_user")
          rider = rider ? JSON.parse(rider) : null

          setRiderId(rider ? rider.id : null)
        }

        getCurrentRiderId()

        fetch(
          `${process.env.REACT_APP_API_URL}/purchases/requestedPurchase`,
          {
              headers: {'Access-Control-Allow-Origin': '*'}
          }
        )
        .then(response => response.json())
        .then(data => {
            setDeliveries(data)
        })
        .catch((reason) => {
            console.log(reason)
            setFetchError(true)
        })
      }, [])
    );
    
    return(
      riderID == null ?
      <View style={styles.container}>
        <Text>Please login first</Text>
      </View>
      :
      fetchError ?
      <Text>An error ocurred fetching data</Text>
      :
      deliveries != null ?
      <View style={styles.container}>
        {
          deliveries.length == 0 ?
          <Text style={styles.title}>No Available Deliveries</Text>
          :
          <>
            <Text style={styles.title}>Available Deliveries</Text>
            <View style={styles.list}>
              <DeliveryList items={deliveries} />
            </View>
          </>
        }
      </View>
      :
      <View style={styles.container}>
        <Text style={styles.title}>Fetching data...</Text>
      </View>
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