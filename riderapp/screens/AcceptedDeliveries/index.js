import { StyleSheet, Text, View, Button } from 'react-native';
import DeliveryList from '../../components/DeliveryList';


const TEST_DATA = [
  {
    id: 14,
    name: "a spectacular delivery"
  },
  {
    id: 16,
    name: "a rather average delivery"
  },
  {
    id: 17,
    name: "the most mundane delivery"
  }
];

const AcceptedDeliveriesScreen = ({navigation}) => {
  
    return(
      <View style={styles.container}>
        <Text style={styles.title}>Accepted Deliveries</Text>
        
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

export default AcceptedDeliveriesScreen;