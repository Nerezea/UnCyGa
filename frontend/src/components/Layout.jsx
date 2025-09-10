import Header from '../components/Header';
import Footer from '../components/Footer';
import PropTypes from 'prop-types';

const Layout = ({ children }) => {
  return (
    <>
      <div className="flexbox-container">
        <Header />
        {children}
        <Footer />
      </div>
    </>
  );
};

Layout.propTypes = {
  children: PropTypes.node.isRequired,
};

export default Layout;
