import { useState } from 'react';
import { StyleSheet, View, Text, Button } from 'react-native';

const DeliveryDetailsScreen = ({ route, navigation }) => {
    const { deliveryId } = route.params;

    console.log(deliveryId);

    const [riderID, setPostId] = useState(1);

    const acceptOrder = () =>{
      const requestOptions = {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ title: 'React Hooks PUT Request Example' })
      };
      fetch(`http://localhost:8080/api/purchases/confirmPurchase/${deliveryId}?riderId=${riderID}`, requestOptions)
          .then(response => response.json())
          .then(data => riderID);

      
    }
  
    return(
      <View style={styles.container}>
        <Text style={styles.title}>Delivery {deliveryId}</Text>

        <Button style={styles.button} title='Accept' onPress={acceptOrder} />
        <View style={styles.separator}></View>
        <Button style={styles.button} title='Complete Delivery' onPress={()=>{}} />
      </View>
    )
};

const styles = StyleSheet.create({
    title: {
      fontSize: 20
    },
    container: {
      padding: 16
    },
    separator: {
      marginVertical: 4
    }
});

export default DeliveryDetailsScreen;