
import React from 'react';
import {
 SafeAreaView,
 StatusBar,
 StyleSheet,
 Text,
 useColorScheme,
 View,
 Button
} from 'react-native';

import {
 Colors,
} from 'react-native/Libraries/NewAppScreen';

import { NotificationModule } from './src/modules/NotificationModule';

function App(): JSX.Element {
 const isDarkMode = useColorScheme() === 'dark';

 const newNotification = React.useCallback(() => {
  NotificationModule.showNotification({
    channelId: 'INFO',
    notificationTitle: 'Rendercon Notification',
    notificationText: 'Hooray! It works!!!'
  })
    .then((_) => {
      console.log('Notification Success!');
    })
    .catch((err) => {
      console.error(err);
    });
 }, []);

 const backgroundStyle = {
  backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
 };

 return (
  <SafeAreaView style={backgroundStyle}>
   <StatusBar
    barStyle={isDarkMode ? 'light-content' : 'dark-content'}
    backgroundColor={backgroundStyle.backgroundColor}
   />
   <View style={styles.sectionContainer}>
    <Text style={styles.sectionTitle}>Home</Text>
   </View>

   <View style={styles.sectionContainer}>
    <Button onPress={newNotification} title='Create Notification' />
   </View>

  </SafeAreaView>
 );
}

const styles = StyleSheet.create({
 sectionContainer: {
  marginTop: 32,
  paddingHorizontal: 24,
 },
 sectionTitle: {
  fontSize: 24,
  fontWeight: '600',
 },
 sectionDescription: {
  marginTop: 8,
  fontSize: 18,
  fontWeight: '500',
 },
 highlight: {
  fontWeight: '700',
 },
});

export default App;

