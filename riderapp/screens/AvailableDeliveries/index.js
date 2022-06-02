import { StyleSheet, Text, View, Button } from 'react-native';
import DeliveryList from '../../components/DeliveryList';


const TEST_DATA = [
  {
    id: 10,
    name: "a cool delivery"
  },
  {
    id: 11,
    name: "a lame delivery"
  }
];

const AvailableDeliveriesScreen = ({navigation}) => {
  
    return(
      <View style={styles.container}>
        <Text style={styles.title}>Available Deliveries</Text>
        
        <View style={styles.list}>
          <DeliveryList items={TEST_DATA} />
        </View>
        
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