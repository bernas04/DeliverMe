import { StyleSheet, View, Text, Button } from 'react-native';

const DeliveryDetailsScreen = ({ route, navigation }) => {
    const { deliveryId } = route.params;
  
    return(
      <View style={styles.container}>
        <Text style={styles.title}>Delivery {deliveryId}</Text>

        <Button style={styles.button} title='Accept' onPress={()=>{}} />
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