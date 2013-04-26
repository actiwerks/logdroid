/**
 * This file is part of the ObjectForms for Android framework and is protected by a copyright.
 * You may not use this file except in compliance with the license, available at 
 *  
 * http://www.objectforms.com/license.html
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package actiwerks.logdroid;

import android.util.Log;
import objectforms.utils.prn;
import objectforms.utils.LogPrinter;

/**
 * Connector of platform neutral prn log tool to native Android log system
 * @author plahoda
 */
public class LogPlug implements LogPrinter {
	
	public void log(String text) {
		// connect the log with the native Android logging system
		Log.i(prn.cc(1), text);
	}
}
